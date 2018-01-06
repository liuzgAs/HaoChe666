package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2018/1/6/006.
 *
 * @author ZhangJieBo
 */

public class LiaoTian {
    /**
     * conversationTitle :
     * conversationType : PRIVATE
     * draft :
     * isTop : false
     * latestMessage : {"content":"八九块就咯","userInfo":{"id":"6","name":"大吉大利","portraitUri":{"cachedFsi":-2,"cachedSsi":-2,"host":"NOT CACHED","port":-2,"scheme":"NOT CACHED","uriString":"https://www.haoche666.com/Uploads/attachment/20171229/a41b88a0ac3448443ffd635019b2ac88.jpg"}}}
     * latestMessageId : 22
     * mentionedCount : 0
     * notificationStatus : NOTIFY
     * objectName : RC:TxtMsg
     * portraitUrl :
     * receivedStatus : {"flag":1,"isDownload":false,"isListened":false,"isMultipleReceive":false,"isRead":true,"isRetrieved":false}
     * receivedTime : 1515220778156
     * senderUserId : 6
     * sentStatus : SENT
     * sentTime : 1515220778271
     * targetId : 19
     * unreadMessageCount : 0
     */

    private String conversationTitle;
    private String conversationType;
    private String draft;
    private boolean isTop;
    private LatestMessageBean latestMessage;
    private int latestMessageId;
    private int mentionedCount;
    private String notificationStatus;
    private String objectName;
    private String portraitUrl;
    private ReceivedStatusBean receivedStatus;
    private long receivedTime;
    private String senderUserId;
    private String sentStatus;
    private long sentTime;
    private String targetId;
    private String nickName;
    private int unreadMessageCount;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getConversationTitle() {
        return conversationTitle;
    }

    public void setConversationTitle(String conversationTitle) {
        this.conversationTitle = conversationTitle;
    }

    public String getConversationType() {
        return conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public LatestMessageBean getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(LatestMessageBean latestMessage) {
        this.latestMessage = latestMessage;
    }

    public int getLatestMessageId() {
        return latestMessageId;
    }

    public void setLatestMessageId(int latestMessageId) {
        this.latestMessageId = latestMessageId;
    }

    public int getMentionedCount() {
        return mentionedCount;
    }

    public void setMentionedCount(int mentionedCount) {
        this.mentionedCount = mentionedCount;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public ReceivedStatusBean getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(ReceivedStatusBean receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public long getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(long receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(String sentStatus) {
        this.sentStatus = sentStatus;
    }

    public long getSentTime() {
        return sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(int unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public static class LatestMessageBean {
        /**
         * content : 八九块就咯
         * userInfo : {"id":"6","name":"大吉大利","portraitUri":{"cachedFsi":-2,"cachedSsi":-2,"host":"NOT CACHED","port":-2,"scheme":"NOT CACHED","uriString":"https://www.haoche666.com/Uploads/attachment/20171229/a41b88a0ac3448443ffd635019b2ac88.jpg"}}
         */

        private String content;
        private UserInfoBean userInfo;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * id : 6
             * name : 大吉大利
             * portraitUri : {"cachedFsi":-2,"cachedSsi":-2,"host":"NOT CACHED","port":-2,"scheme":"NOT CACHED","uriString":"https://www.haoche666.com/Uploads/attachment/20171229/a41b88a0ac3448443ffd635019b2ac88.jpg"}
             */

            private String id;
            private String name;
            private PortraitUriBean portraitUri;

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

            public PortraitUriBean getPortraitUri() {
                return portraitUri;
            }

            public void setPortraitUri(PortraitUriBean portraitUri) {
                this.portraitUri = portraitUri;
            }

            public static class PortraitUriBean {
                /**
                 * cachedFsi : -2
                 * cachedSsi : -2
                 * host : NOT CACHED
                 * port : -2
                 * scheme : NOT CACHED
                 * uriString : https://www.haoche666.com/Uploads/attachment/20171229/a41b88a0ac3448443ffd635019b2ac88.jpg
                 */

                private int cachedFsi;
                private int cachedSsi;
                private String host;
                private int port;
                private String scheme;
                private String uriString;

                public int getCachedFsi() {
                    return cachedFsi;
                }

                public void setCachedFsi(int cachedFsi) {
                    this.cachedFsi = cachedFsi;
                }

                public int getCachedSsi() {
                    return cachedSsi;
                }

                public void setCachedSsi(int cachedSsi) {
                    this.cachedSsi = cachedSsi;
                }

                public String getHost() {
                    return host;
                }

                public void setHost(String host) {
                    this.host = host;
                }

                public int getPort() {
                    return port;
                }

                public void setPort(int port) {
                    this.port = port;
                }

                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }

                public String getUriString() {
                    return uriString;
                }

                public void setUriString(String uriString) {
                    this.uriString = uriString;
                }
            }
        }
    }

    public static class ReceivedStatusBean {
        /**
         * flag : 1
         * isDownload : false
         * isListened : false
         * isMultipleReceive : false
         * isRead : true
         * isRetrieved : false
         */

        private int flag;
        private boolean isDownload;
        private boolean isListened;
        private boolean isMultipleReceive;
        private boolean isRead;
        private boolean isRetrieved;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isIsDownload() {
            return isDownload;
        }

        public void setIsDownload(boolean isDownload) {
            this.isDownload = isDownload;
        }

        public boolean isIsListened() {
            return isListened;
        }

        public void setIsListened(boolean isListened) {
            this.isListened = isListened;
        }

        public boolean isIsMultipleReceive() {
            return isMultipleReceive;
        }

        public void setIsMultipleReceive(boolean isMultipleReceive) {
            this.isMultipleReceive = isMultipleReceive;
        }

        public boolean isIsRead() {
            return isRead;
        }

        public void setIsRead(boolean isRead) {
            this.isRead = isRead;
        }

        public boolean isIsRetrieved() {
            return isRetrieved;
        }

        public void setIsRetrieved(boolean isRetrieved) {
            this.isRetrieved = isRetrieved;
        }
    }
}
