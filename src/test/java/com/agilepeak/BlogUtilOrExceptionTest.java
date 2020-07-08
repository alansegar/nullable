package com.agilepeak;

import com.agilepeak.blog.BlogPost;
import com.agilepeak.blog.BlogUtil;
import com.agilepeak.blog.CustomNotFoundException;
import com.agilepeak.blog.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BlogUtilOrExceptionTest {

    @Test
    public void extractJobTitleOrException_handles_populated_field() {
        Person person = Person.builder().firstName("Test").lastName("Testerson").jobTitle("Blog post author").build();
        BlogPost blogPost = BlogPost.builder().author(person).build();
        assertThat(BlogUtil.extractJobTitleOrExceptionUsingOptional(blogPost)).isEqualTo("Blog post author");
        assertThat(BlogUtil.extractJobTitleOrException(blogPost)).isEqualTo("Blog post author");
    }

    @Test
    public void extractJobTitleOrException_throws_for_null_blog_post() {
        assertThatThrownBy(() -> BlogUtil.extractJobTitleOrExceptionUsingOptional(null))
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage("Cannot extract jobTitle from blog post");

        assertThatThrownBy(() -> BlogUtil.extractJobTitleOrException(null))
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage("Cannot extract jobTitle from blog post");
    }

    @Test
    public void extractJobTitleOrException_throws_for_null_author() {
        BlogPost blogPost = BlogPost.builder().author(null).build();
        assertThatThrownBy(() -> BlogUtil.extractJobTitleOrExceptionUsingOptional(blogPost))
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage("Cannot extract jobTitle from blog post");

        assertThatThrownBy(() -> BlogUtil.extractJobTitleOrException(blogPost))
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage("Cannot extract jobTitle from blog post");
    }

    @Test
    public void extractJobTitleOrException_throws_for_null_job_title() {
        Person person = Person.builder().firstName("Test").lastName("Testerson").jobTitle(null).build();
        BlogPost blogPost = BlogPost.builder().author(person).build();

        assertThatThrownBy(() -> BlogUtil.extractJobTitleOrExceptionUsingOptional(blogPost))
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage("Cannot extract jobTitle from blog post");

        assertThatThrownBy(() -> BlogUtil.extractJobTitleOrException(blogPost))
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage("Cannot extract jobTitle from blog post");
    }
}
