package com.agilepeak.blog;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class BlogPost {
    Person author;
}
