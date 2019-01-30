package com.specter.inspecter.bean;

import java.util.List;

public class InsBean {

    /**
     * id : 2039314310
     * name : galgadotjl
     * profile_pic_url : https://scontent-hkg3-2.cdninstagram.com/vp/eb101f8998ce6c9d7adcec9bae4892ad/5BD4A0AC/t51.2885-19/s150x150/22710196_1340557269406854_7838960436467728384_n.jpg
     * profile_pic_url_hd : https://scontent-hkg3-2.cdninstagram.com/vp/182549016fe9404a3e359d0e58bfdd34/5BD5EDD4/t51.2885-19/s320x320/22710196_1340557269406854_7838960436467728384_n.jpg
     * biography : Alicia's Canadian fan page for Gal Gadot and Wonder Woman, eh
     * postsCount :
     */

    private String id;
    private String name;
    private String profile_pic_url;
    private String profile_pic_url_hd;
    private String biography;
    private int postsCount;
    private List<PostsBean> posts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public String getProfile_pic_url_hd() {
        return profile_pic_url_hd;
    }

    public void setProfile_pic_url_hd(String profile_pic_url_hd) {
        this.profile_pic_url_hd = profile_pic_url_hd;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean {
        /**
         * id : 1832050731348144075
         * dimensions : {"height":750,"width":750}
         * display_url : https://scontent-hkg3-2.cdninstagram.com/vp/2ff12d4ae55507c79c2e716993258b79/5B5CC31C/t51.2885-15/e35/37089336_1948862135412143_5654693769421783040_n.jpg
         * is_video : true
         * taken_at_timestamp : 1532617528
         * edge_liked_count : 1756
         * shortcode : BlswG78gC_L
         * video_view_count : 27590
         * detail : {"id":"1832050731348144075","is_video":true,"video_url":"https://scontent-hkg3-2.cdninstagram.com/vp/28cec2bbc36a8bef370a80ee705c04c8/5B5C5F3D/t50.2886-16/37671030_272755243533378_4188499768311283712_n.mp4","video_duration":23.11,"taken_at_timestamp":1532617528,"display_url":"https://scontent-hkg3-2.cdninstagram.com/vp/2ff12d4ae55507c79c2e716993258b79/5B5CC31C/t51.2885-15/e35/37089336_1948862135412143_5654693769421783040_n.jpg","dimensions":{"height":750,"width":750},"edge_media_to_caption_text":"Gal dancing = Almost Friday!☺❤\n.\n.\nCredit to owners/Castro. Edited. ","edge_sidecar_to_children":[]}
         */

        private String id;
        private DimensionsBean dimensions;
        private String display_url;
        private boolean is_video;
        private int taken_at_timestamp;
        private int edge_liked_count;
        private String shortcode;
        private int video_view_count;
        private DetailBean detail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public DimensionsBean getDimensions() {
            return dimensions;
        }

        public void setDimensions(DimensionsBean dimensions) {
            this.dimensions = dimensions;
        }

        public String getDisplay_url() {
            return display_url;
        }

        public void setDisplay_url(String display_url) {
            this.display_url = display_url;
        }

        public boolean isIs_video() {
            return is_video;
        }

        public void setIs_video(boolean is_video) {
            this.is_video = is_video;
        }

        public int getTaken_at_timestamp() {
            return taken_at_timestamp;
        }

        public void setTaken_at_timestamp(int taken_at_timestamp) {
            this.taken_at_timestamp = taken_at_timestamp;
        }

        public int getEdge_liked_count() {
            return edge_liked_count;
        }

        public void setEdge_liked_count(int edge_liked_count) {
            this.edge_liked_count = edge_liked_count;
        }

        public String getShortcode() {
            return shortcode;
        }

        public void setShortcode(String shortcode) {
            this.shortcode = shortcode;
        }

        public int getVideo_view_count() {
            return video_view_count;
        }

        public void setVideo_view_count(int video_view_count) {
            this.video_view_count = video_view_count;
        }

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DimensionsBean {
            /**
             * height : 750
             * width : 750
             */

            private int height;
            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }

        public static class DetailBean {
            /**
             * id : 1832050731348144075
             * is_video : true
             * video_url : https://scontent-hkg3-2.cdninstagram.com/vp/28cec2bbc36a8bef370a80ee705c04c8/5B5C5F3D/t50.2886-16/37671030_272755243533378_4188499768311283712_n.mp4
             * video_duration : 23.11
             * taken_at_timestamp : 1532617528
             * display_url : https://scontent-hkg3-2.cdninstagram.com/vp/2ff12d4ae55507c79c2e716993258b79/5B5CC31C/t51.2885-15/e35/37089336_1948862135412143_5654693769421783040_n.jpg
             * dimensions : {"height":750,"width":750}
             * edge_media_to_caption_text : Gal dancing = Almost Friday!☺❤
             .
             .
             Credit to owners/Castro. Edited.
             * edge_sidecar_to_children : []
             */

            private String id;
            private boolean is_video;
            private String video_url;
            private double video_duration;
            private int taken_at_timestamp;
            private String display_url;
            private DimensionsBeanX dimensions;
            private String edge_media_to_caption_text;
            private List<?> edge_sidecar_to_children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIs_video() {
                return is_video;
            }

            public void setIs_video(boolean is_video) {
                this.is_video = is_video;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public double getVideo_duration() {
                return video_duration;
            }

            public void setVideo_duration(double video_duration) {
                this.video_duration = video_duration;
            }

            public int getTaken_at_timestamp() {
                return taken_at_timestamp;
            }

            public void setTaken_at_timestamp(int taken_at_timestamp) {
                this.taken_at_timestamp = taken_at_timestamp;
            }

            public String getDisplay_url() {
                return display_url;
            }

            public void setDisplay_url(String display_url) {
                this.display_url = display_url;
            }

            public DimensionsBeanX getDimensions() {
                return dimensions;
            }

            public void setDimensions(DimensionsBeanX dimensions) {
                this.dimensions = dimensions;
            }

            public String getEdge_media_to_caption_text() {
                return edge_media_to_caption_text;
            }

            public void setEdge_media_to_caption_text(String edge_media_to_caption_text) {
                this.edge_media_to_caption_text = edge_media_to_caption_text;
            }

            public List<?> getEdge_sidecar_to_children() {
                return edge_sidecar_to_children;
            }

            public void setEdge_sidecar_to_children(List<?> edge_sidecar_to_children) {
                this.edge_sidecar_to_children = edge_sidecar_to_children;
            }

            public static class DimensionsBeanX {
                /**
                 * height : 750
                 * width : 750
                 */

                private int height;
                private int width;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }
        }
    }
}
