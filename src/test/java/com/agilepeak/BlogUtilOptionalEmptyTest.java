package com.agilepeak;

import com.agilepeak.blog.BlogPost;
import com.agilepeak.blog.BlogUtil;
import com.agilepeak.blog.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlogUtilOptionalEmptyTest {

    @Test
    public void extractOptionalJobTitleAsNull_handles_populated_field() {
        Person person = Person.builder().firstName("Test").lastName("Testerson").jobTitle("Blog post author").build();
        BlogPost blogPost = BlogPost.builder().author(person).build();
        assertThat(BlogUtil.extractNullableJobTitleAsOptional(blogPost)).contains("Blog post author");
        assertThat(BlogUtil.extractNullableJobTitleAsNull(blogPost)).isEqualTo("Blog post author");
    }

    @Test
    public void extractOptionalJobTitleAsNull_for_null_blog_post() {
        assertThat(BlogUtil.extractNullableJobTitleAsOptional(null)).isEmpty();
        assertThat(BlogUtil.extractNullableJobTitleAsNull(null)).isNull();
    }

    @Test
    public void extractOptionalJobTitleAsNull_for_null_author() {
        BlogPost blogPost = BlogPost.builder().author(null).build();
        assertThat(BlogUtil.extractNullableJobTitleAsOptional(blogPost)).isEmpty();
        assertThat(BlogUtil.extractNullableJobTitleAsNull(blogPost)).isNull();
    }

    @Test
    public void extractOptionalJobTitleAsNull_for_null_job_title() {
        Person person = Person.builder().firstName("Test").lastName("Testerson").jobTitle(null).build();
        BlogPost blogPost = BlogPost.builder().author(person).build();
        assertThat(BlogUtil.extractNullableJobTitleAsOptional(blogPost)).isEmpty();
        assertThat(BlogUtil.extractNullableJobTitleAsNull(blogPost)).isNull();
    }
}
