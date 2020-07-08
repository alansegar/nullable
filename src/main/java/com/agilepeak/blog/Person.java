package com.agilepeak.blog;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class Person {
    String firstName;
    String lastName;
    String jobTitle;
}
