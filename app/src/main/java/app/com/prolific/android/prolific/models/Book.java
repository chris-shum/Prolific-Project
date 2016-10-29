
package app.com.prolific.android.prolific.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Book {

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("categories")
    @Expose
    private String categories;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("lastCheckedOut")
    @Expose
    private String lastCheckedOut;
    @SerializedName("lastCheckedOutBy")
    @Expose
    private String lastCheckedOutBy;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public String getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The lastCheckedOut
     */
    public String getLastCheckedOut() {
        return lastCheckedOut;
    }

    /**
     * 
     * @param lastCheckedOut
     *     The lastCheckedOut
     */
    public void setLastCheckedOut(String lastCheckedOut) {
        this.lastCheckedOut = lastCheckedOut;
    }

    /**
     * 
     * @return
     *     The lastCheckedOutBy
     */
    public String getLastCheckedOutBy() {
        return lastCheckedOutBy;
    }

    /**
     * 
     * @param lastCheckedOutBy
     *     The lastCheckedOutBy
     */
    public void setLastCheckedOutBy(String lastCheckedOutBy) {
        this.lastCheckedOutBy = lastCheckedOutBy;
    }

    /**
     * 
     * @return
     *     The publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
