package com.sohan.projects.lovable_clone.entity;

import com.sohan.projects.lovable_clone.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    Long id;

    ChatSession chatSession;

    String content;

    MessageRole messageRole;

    String toolCalls; // JSON Array of Tools Called
    Integer tokensUsed;

    Instant createdAt;
}
