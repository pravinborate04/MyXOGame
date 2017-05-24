package com.pravin.myxogame;

/**
 * Created by Pravin Borate on 4/4/17.
 */

public class SendFcmModel
{

    /**
     * data : {"title":"Your Titlqqqqqe","text":"Your Tqqqqqqqext"}
     * to : cjJ_d8LnIGU:APA91bGdC7MAZsuQcocKfvKFGGg5QOQo1cN8pCzLMPE4_QeAD6JM3HtkiP1rKzr9Gvk65JJ2kd3tfjmT0-23bI5tT601cHIKn_iKqrs1ATIzwKx5Xt1l9n1y0sR51kVaWUa_X31CQFqg
     */

    private DataEntity data;
    private String to;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static class DataEntity {
        /**
         * title : Your Titlqqqqqe
         * text : Your Tqqqqqqqext
         */

        private String title;
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
