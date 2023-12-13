package com.morecommit.carrotEz.message;

import lombok.*;

@Getter @Setter
@ToString @NoArgsConstructor @AllArgsConstructor
public class Message {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
