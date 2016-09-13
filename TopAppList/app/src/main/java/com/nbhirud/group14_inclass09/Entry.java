package com.nbhirud.group14_inclass09;

/**
 * Created by nbhirud on 6/23/2016.
 */
public class Entry {
    //id, app title, developer name, url, small photo url, large photo url, and app price
    private String id;
    private String appURL;
    private String appName; //<im:name>Spotify Music</im:name>
    private String developerName; //<im:artist href="https://itunes.apple.com/us/developer/spotify-ltd./id324684583?mt=8&uo=2">Spotify Ltd.</im:artist>
    private String Releasedate; //<im:releaseDate label="July 14, 2011">2011-07-14T04:22:37-07:00</im:releaseDate>
    private String price;   //<im:price amount="0.00000" currency="USD">Get</im:price>
    private String category; //<category im:id="6014" term="Games" scheme="https://itunes.apple.com/us/genre/ios-games/id6014?mt=8&uo=2" label="Games"/>
    private String imageURL53; //     <im:image height="53">http://is3.mzstatic.com/image/thumb/Purple18/v4/93/8c/f1/938cf1f7-aec7-9481-fb79-7bb14d6ee5b0/mzl.nautzuix.png/53x53bb-85.png</im:image>
    private String imageURL100;
    private boolean isFav = false;

    public Entry() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Entry(String id, String appURL, String appName, String developerName, String releaseDate, String price, String category, String imageURL53, String imageURL100, boolean isFav) {
        this.id = id;
        this.appURL = appURL;
        this.appName = appName;
        this.developerName = developerName;
        Releasedate = releaseDate;
        this.price = price;
        this.category = category;
        this.imageURL53 = imageURL53;
        this.imageURL100 = imageURL100;
        this.isFav = isFav;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public int getStarImage() {
        if (this.isFav == true) {
            return R.drawable.ic_yellow_star;
        }
        else {
            return R.drawable.ic_grey_star;
        }
    }
    //Bitmap img53;
    //Bitmap img100;
/*
    public Bitmap getImg53() {
        return img53;
    }

    public void setImg53(Bitmap img53) {
        this.img53 = img53;
    }

    public Bitmap getImg100() {
        return img100;
    }

    public void setImg100(Bitmap img100) {
        this.img100 = img100;
    }
    */
    /*
    <im:image height="53">
http://is3.mzstatic.com/image/thumb/Purple18/v4/93/8c/f1/938cf1f7-aec7-9481-fb79-7bb14d6ee5b0/mzl.nautzuix.png/53x53bb-85.png
</im:image>
<im:image height="75">
http://is5.mzstatic.com/image/thumb/Purple18/v4/93/8c/f1/938cf1f7-aec7-9481-fb79-7bb14d6ee5b0/mzl.nautzuix.png/75x75bb-85.png
</im:image>
<im:image height="100">
http://is3.mzstatic.com/image/thumb/Purple18/v4/93/8c/f1/938cf1f7-aec7-9481-fb79-7bb14d6ee5b0/mzl.nautzuix.png/100x100bb-85.png
</im:image>
*/

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getReleasedate() {
        return Releasedate;
    }

    public void setReleasedate(String releaseDate) {
        Releasedate = releaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL53() {
        return imageURL53;
    }

    public void setImageURL53(String imageURL53) {
        this.imageURL53 = imageURL53;
    }

    public String getImageURL100() {
        return imageURL100;
    }

    public void setImageURL100(String imageURL100) {
        this.imageURL100 = imageURL100;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppURL() {
        return appURL;
    }

    public void setAppURL(String appURL) {
        this.appURL = appURL;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id='" + id + '\'' +
                ", appURL='" + appURL + '\'' +
                ", appName='" + appName + '\'' +
                ", developerName='" + developerName + '\'' +
                ", ReleaseDate='" + Releasedate + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", imageURL53='" + imageURL53 + '\'' +
                ", imageURL100='" + imageURL100 + '\'' +
                ", isFav=" + isFav +
                '}'+
                '\n';
    }

/*
    public Bitmap processImage(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Bitmap img = BitmapFactory.decodeStream(con.getInputStream());
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
