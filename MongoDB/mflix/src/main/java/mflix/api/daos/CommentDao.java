package mflix.api.daos;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import mflix.api.models.Comment;
import mflix.api.models.Critic;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Component
public class CommentDao extends AbstractMFlixDao {

    public static String COMMENT_COLLECTION = "comments";
    private final Logger log;
    private MongoCollection<Comment> commentCollection;
    private CodecRegistry pojoCodecRegistry;

    @Autowired
    public CommentDao(
            MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        log = LoggerFactory.getLogger(this.getClass());
        this.db = this.mongoClient.getDatabase(MFLIX_DATABASE);
        this.pojoCodecRegistry =
                fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        this.commentCollection =
                db.getCollection(COMMENT_COLLECTION, Comment.class).withCodecRegistry(pojoCodecRegistry);
    }

    /**
     * Returns a Comment object that matches the provided id string.
     *
     * @param id - comment identifier
     * @return Comment object corresponding to the identifier value
     */
    public Comment getComment(String id) {
        return commentCollection.find(new Document("_id", new ObjectId(id))).first();
    }

    /**
     * Adds a new Comment to the collection. The equivalent instruction in the mongo shell would be:
     *
     * <p>db.comments.insertOne({comment})
     *
     * <p>
     *
     * @param comment - Comment object.
     * @throw IncorrectDaoOperation if the insert fails, otherwise
     * returns the resulting Comment object.
     */
    public Comment addComment(Comment comment) {
        if(comment.getId()==null || comment.getId().isEmpty()){
            throw new IncorrectDaoOperation("commentID cannot be set null");
        }

        try{
            commentCollection.insertOne(comment);
            return comment;
        }catch (MongoWriteException e){
            String errorMessage =
                    MessageFormat.format(
                            "Error occurred while adding a new Comment `{}`: {}", comment, e.getMessage());
            throw new IncorrectDaoOperation(errorMessage);
        }

        // TODO> Ticket - Update User reviews: implement the functionality that enables adding a new
        // comment.
        // TODO> Ticket - Handling Errors: Implement a try catch block to
        // handle a potential write exception when given a wrong commentId.
    }

    /**
     * Updates the comment text matching commentId and user email. This method would be equivalent to
     * running the following mongo shell command:
     *
     * <p>db.comments.update({_id: commentId}, {$set: { "text": text, date: ISODate() }})
     *
     * <p>
     *
     * @param commentId - comment id string value.
     * @param text      - comment text to be updated.
     * @param email     - user email.
     * @return true if successfully updates the comment text.
     */
    public boolean updateComment(String commentId, String text, String email) {

        Bson filter =
                Filters.and(Filters.eq("email", email), Filters.eq("_id", new ObjectId(commentId)));
        Bson update = Updates.combine(Updates.set("text", text), Updates.set("date", new Date()));
        try {

            UpdateResult res = commentCollection.updateOne(filter, update);

            if (res.getMatchedCount() > 0) {

                if (res.getModifiedCount() != 1) {
                    log.warn("Comment `{}` text was not updated. Is it the same text?");
                }

                return true;
            }
            log.error(
                    "Could not update comment `{}`. Make sure the comment is owned by `{}`",
                    commentId,
                    email);
            return false;

        } catch (MongoWriteException e) {
            String messageError =
                    MessageFormat.format(
                            "Error occurred while updating comment `{}`: {}", commentId, e.getMessage());
            throw new IncorrectDaoOperation(messageError);
        }
//        try{
//            Bson updateFilter = new Document("email", email);
//            Bson updateObject = Updates.set("preferences", userPreferences);
//            UpdateResult res = usersCollection.updateOne(updateFilter, updateObject);
//
//        }catch (MongoException e){
//            e.printStackTrace();
//        }
        // TODO> Ticket - Update User reviews: implement the functionality that enables updating an
        // user own comments
        // TODO> Ticket - Handling Errors: Implement a try catch block to
        // handle a potential write exception when given a wrong commentId.

    }

    /**
     * Deletes comment that matches user email and commentId.
     *
     * @param commentId - commentId string value.
     * @param email     - user email value.
     * @return true if successful deletes the comment.
     */
    public boolean deleteComment(String commentId, String email) {
        // TODO> Ticket Delete Comments - Implement the method that enables the deletion of a user
        Bson filter = Filters.and(Filters.eq("email", email),
                        Filters.eq("_id", new ObjectId(commentId)));

        try {
            DeleteResult res = commentCollection.deleteOne(filter);
            if (res.getDeletedCount() != 1) {
                log.warn(
                        "Not able to delete comment `{}` for user `{}`. User"
                                + " does not own comment or already deleted!",
                        commentId,
                        email);
                return false;
            }
            return true;
        } catch (MongoWriteException e) {
            String errorMessage =
                    MessageFormat.format("Error deleting comment " + "`{}`: {}", commentId, e);
            throw new IncorrectDaoOperation(errorMessage);
        }
        // comment
        // TIP: make sure to match only users that own the given commentId
        // TODO> Ticket Handling Errors - Implement a try catch block to
        // handle a potential write exception when given a wrong commentId.
    }

    /**
     * Ticket: User Report - produce a list of users that comment the most in the website. Query the
     * `comments` collection and group the users by number of comments. The list is limited to up most
     * 20 commenter.
     *
     * @return List {@link Critic} objects.
     */
    public List<Critic> mostActiveCommenters() {
        List<Critic> mostActive = new ArrayList<>();
        // // TODO> Ticket: User Report - execute a command that returns the


        Bson groupByCountStage = Aggregates.sortByCount("$email");
        Bson sortStage = Aggregates.sort(Sorts.descending("count"));
        Bson limitStage = Aggregates.limit(20);

        List<Bson> pipeline = new ArrayList<>();

        pipeline.add(groupByCountStage);
        pipeline.add(sortStage);
        pipeline.add(limitStage);

        commentCollection
                .withReadConcern(ReadConcern.MAJORITY)
                .aggregate(pipeline, Critic.class)
                .iterator()
                .forEachRemaining(mostActive::add);
        // // list of 20 users, group by number of comments. Don't forget,
        // // this report is expected to be produced with an high durability
        // // guarantee for the returned documents. Once a commenter is in the
        // // top 20 of users, they become a Critic, so mostActive is composed of
        // // Critic objects.
        return mostActive;
    }
}
