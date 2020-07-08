package com.agilepeak;

import com.agilepeak.blog.BlogPost;
import com.agilepeak.blog.BlogUtil;
import com.agilepeak.blog.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlogUtilWithDefaultTest {

    @Test
    public void extractJobTitleWithDefault_handles_populated_field() {
        Person person = Person.builder().firstName("Test").lastName("Testerson").jobTitle("Blog post author").build();
        BlogPost blogPost = BlogPost.builder().author(person).build();
        assertThat(BlogUtil.extractJobTitleWithDefaultUsingOptional(blogPost)).isEqualTo("Blog post author");
        assertThat(BlogUtil.extractJobTitleWithDefault(blogPost)).isEqualTo("Blog post author");
    }

    @Test
    public void extractJobTitleWithDefault_returns_default_for_null_blog_post() {
        assertThat(BlogUtil.extractJobTitleWithDefaultUsingOptional(null)).isEqualTo("Unknown");
        assertThat(BlogUtil.extractJobTitleWithDefault(null)).isEqualTo("Unknown");
    }

    @Test
    public void extractJobTitleWithDefault_returns_default_for_null_author() {
        BlogPost blogPost = BlogPost.builder().author(null).build();
        assertThat(BlogUtil.extractJobTitleWithDefaultUsingOptional(blogPost)).isEqualTo("Unknown");
        assertThat(BlogUtil.extractJobTitleWithDefault(blogPost)).isEqualTo("Unknown");
    }

    @Test
    public void extractJobTitleWithDefault_returns_default_for_null_job_title() {
        Person person = Person.builder().firstName("Test").lastName("Testerson").jobTitle(null).build();
        BlogPost blogPost = BlogPost.builder().author(person).build();
        assertThat(BlogUtil.extractJobTitleWithDefaultUsingOptional(blogPost)).isEqualTo("Unknown");
        assertThat(BlogUtil.extractJobTitleWithDefault(blogPost)).isEqualTo("Unknown");
    }
}
