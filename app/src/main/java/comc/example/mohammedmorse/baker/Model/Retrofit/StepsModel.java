package comc.example.mohammedmorse.baker.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StepsModel implements Serializable{
    @SerializedName("id")
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        ShortDesc = shortDesc;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public String getDescribtion() {
        return Describtion;
    }

    public void setDescribtion(String describtion) {
        Describtion = describtion;
    }

    public String getThumbnailUrl() {
        return ThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        ThumbnailUrl = thumbnailUrl;
    }

    @SerializedName("shortDescription")
    String ShortDesc;
    @SerializedName("videoURL")
    String VideoUrl;
    @SerializedName("description")
    String Describtion;
    @SerializedName("thumbnailURL")
    String ThumbnailUrl;
}
