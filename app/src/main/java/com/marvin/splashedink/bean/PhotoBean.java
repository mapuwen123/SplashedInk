package com.marvin.splashedink.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class PhotoBean {

    /**
     * id : OL5cqauxUS4
     * created_at : 2017-06-08T05:07:49-04:00
     * updated_at : 2017-07-11T03:30:08-04:00
     * width : 1600
     * height : 2000
     * color : #7AACCE
     * likes : 138
     * liked_by_user : false
     * description : null
     * user : {"id":"TDfYMpwQTho","updated_at":"2017-07-11T03:30:08-04:00","username":"thesollers","name":"Anton Darius | Sollers","first_name":"Anton","last_name":"Darius | Sollers","twitter_username":"DariusAnton","portfolio_url":"https://creativemarket.com/sollersphotography","bio":"I love bokeh! I LOVE UNSPLASH ","location":"Brasov , Romania","total_likes":354,"total_photos":198,"total_collections":4,"profile_image":{"small":"https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=1e9e2111a1126b4a4a034250a6f07049","medium":"https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=4664aba3a331aebce74d5b2f7824882e","large":"https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=72134f04a2aeece19803918aa47fb4f3"},"links":{"self":"https://api.unsplash.com/users/thesollers","html":"http://unsplash.com/@thesollers","photos":"https://api.unsplash.com/users/thesollers/photos","likes":"https://api.unsplash.com/users/thesollers/likes","portfolio":"https://api.unsplash.com/users/thesollers/portfolio","following":"https://api.unsplash.com/users/thesollers/following","followers":"https://api.unsplash.com/users/thesollers/followers"}}
     * current_user_collections : []
     * urls : {"raw":"https://images.unsplash.com/photo-1496912819863-5055eb23c282","full":"https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=094fdda8c9a5ba39c7ca3a564bc0147b","regular":"https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=3e2c98c59fc9fda9fab682e0deed218d","small":"https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=c22feb924610f480c159543f82a61149","thumb":"https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=fd22fe4c5f7be38f29322d956b048168"}
     * categories : []
     * links : {"self":"https://api.unsplash.com/photos/OL5cqauxUS4","html":"http://unsplash.com/photos/OL5cqauxUS4","download":"http://unsplash.com/photos/OL5cqauxUS4/download","download_location":"https://api.unsplash.com/photos/OL5cqauxUS4/download"}
     */

    private String id;
    private String created_at;
    private String updated_at;
    private int width;
    private int height;
    private String color;
    private int likes;
    private boolean liked_by_user;
    private Object description;
    private UserBean user;
    private UrlsBean urls;
    private LinksBeanX links;
    private List<?> current_user_collections;
    private List<?> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public void setLiked_by_user(boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UrlsBean getUrls() {
        return urls;
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }

    public LinksBeanX getLinks() {
        return links;
    }

    public void setLinks(LinksBeanX links) {
        this.links = links;
    }

    public List<?> getCurrent_user_collections() {
        return current_user_collections;
    }

    public void setCurrent_user_collections(List<?> current_user_collections) {
        this.current_user_collections = current_user_collections;
    }

    public List<?> getCategories() {
        return categories;
    }

    public void setCategories(List<?> categories) {
        this.categories = categories;
    }

    public static class UserBean {
        /**
         * id : TDfYMpwQTho
         * updated_at : 2017-07-11T03:30:08-04:00
         * username : thesollers
         * name : Anton Darius | Sollers
         * first_name : Anton
         * last_name : Darius | Sollers
         * twitter_username : DariusAnton
         * portfolio_url : https://creativemarket.com/sollersphotography
         * bio : I love bokeh! I LOVE UNSPLASH
         * location : Brasov , Romania
         * total_likes : 354
         * total_photos : 198
         * total_collections : 4
         * profile_image : {"small":"https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=1e9e2111a1126b4a4a034250a6f07049","medium":"https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=4664aba3a331aebce74d5b2f7824882e","large":"https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=72134f04a2aeece19803918aa47fb4f3"}
         * links : {"self":"https://api.unsplash.com/users/thesollers","html":"http://unsplash.com/@thesollers","photos":"https://api.unsplash.com/users/thesollers/photos","likes":"https://api.unsplash.com/users/thesollers/likes","portfolio":"https://api.unsplash.com/users/thesollers/portfolio","following":"https://api.unsplash.com/users/thesollers/following","followers":"https://api.unsplash.com/users/thesollers/followers"}
         */

        private String id;
        private String updated_at;
        private String username;
        private String name;
        private String first_name;
        private String last_name;
        private String twitter_username;
        private String portfolio_url;
        private String bio;
        private String location;
        private int total_likes;
        private int total_photos;
        private int total_collections;
        private ProfileImageBean profile_image;
        private LinksBean links;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getTwitter_username() {
            return twitter_username;
        }

        public void setTwitter_username(String twitter_username) {
            this.twitter_username = twitter_username;
        }

        public String getPortfolio_url() {
            return portfolio_url;
        }

        public void setPortfolio_url(String portfolio_url) {
            this.portfolio_url = portfolio_url;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getTotal_likes() {
            return total_likes;
        }

        public void setTotal_likes(int total_likes) {
            this.total_likes = total_likes;
        }

        public int getTotal_photos() {
            return total_photos;
        }

        public void setTotal_photos(int total_photos) {
            this.total_photos = total_photos;
        }

        public int getTotal_collections() {
            return total_collections;
        }

        public void setTotal_collections(int total_collections) {
            this.total_collections = total_collections;
        }

        public ProfileImageBean getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(ProfileImageBean profile_image) {
            this.profile_image = profile_image;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public static class ProfileImageBean {
            /**
             * small : https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=1e9e2111a1126b4a4a034250a6f07049
             * medium : https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=4664aba3a331aebce74d5b2f7824882e
             * large : https://images.unsplash.com/profile-1494662322449-b7405a7b9a3c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=72134f04a2aeece19803918aa47fb4f3
             */

            private String small;
            private String medium;
            private String large;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }
        }

        public static class LinksBean {
            /**
             * self : https://api.unsplash.com/users/thesollers
             * html : http://unsplash.com/@thesollers
             * photos : https://api.unsplash.com/users/thesollers/photos
             * likes : https://api.unsplash.com/users/thesollers/likes
             * portfolio : https://api.unsplash.com/users/thesollers/portfolio
             * following : https://api.unsplash.com/users/thesollers/following
             * followers : https://api.unsplash.com/users/thesollers/followers
             */

            private String self;
            private String html;
            private String photos;
            private String likes;
            private String portfolio;
            private String following;
            private String followers;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }

            public String getHtml() {
                return html;
            }

            public void setHtml(String html) {
                this.html = html;
            }

            public String getPhotos() {
                return photos;
            }

            public void setPhotos(String photos) {
                this.photos = photos;
            }

            public String getLikes() {
                return likes;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }

            public String getPortfolio() {
                return portfolio;
            }

            public void setPortfolio(String portfolio) {
                this.portfolio = portfolio;
            }

            public String getFollowing() {
                return following;
            }

            public void setFollowing(String following) {
                this.following = following;
            }

            public String getFollowers() {
                return followers;
            }

            public void setFollowers(String followers) {
                this.followers = followers;
            }
        }
    }

    public static class UrlsBean {
        /**
         * raw : https://images.unsplash.com/photo-1496912819863-5055eb23c282
         * full : https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=094fdda8c9a5ba39c7ca3a564bc0147b
         * regular : https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=3e2c98c59fc9fda9fab682e0deed218d
         * small : https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=c22feb924610f480c159543f82a61149
         * thumb : https://images.unsplash.com/photo-1496912819863-5055eb23c282?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=fd22fe4c5f7be38f29322d956b048168
         */

        private String raw;
        private String full;
        private String regular;
        private String small;
        private String thumb;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }

    public static class LinksBeanX {
        /**
         * self : https://api.unsplash.com/photos/OL5cqauxUS4
         * html : http://unsplash.com/photos/OL5cqauxUS4
         * download : http://unsplash.com/photos/OL5cqauxUS4/download
         * download_location : https://api.unsplash.com/photos/OL5cqauxUS4/download
         */

        private String self;
        private String html;
        private String download;
        private String download_location;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDownload_location() {
            return download_location;
        }

        public void setDownload_location(String download_location) {
            this.download_location = download_location;
        }
    }
}
