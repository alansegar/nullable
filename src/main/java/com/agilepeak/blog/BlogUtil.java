package com.agilepeak.blog;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class BlogUtil {

    /**
     * Example of using Optional.ofNullable with fallback to a default value
     *
     * @param nullableBlogPost input that may either be null, part-populated or fully-populated
     * @return The author's job title if available, otherwise "Unknown"
     */
    public String extractJobTitleWithDefaultUsingOptional(BlogPost nullableBlogPost) {
            // returns default value if nullableBlogPost is null
        return Optional.ofNullable(nullableBlogPost)
            // returns default value if blogPost.author is null
            .map(BlogPost::getAuthor)
            // returns default value if blogPost.author.jobTitle is null
            .map(Person::getJobTitle)
            // defines the default value to return
            .orElse("Unknown");
    }

    /**
     * Example of how fallback to a default value was achieved prior to Optional.ofNullable
     *
     * @param nullableBlogPost input that may either be null, part-populated or fully-populated
     * @return The author's job title if available, otherwise "Unknown"
     */
    public String extractJobTitleWithDefault(BlogPost nullableBlogPost) {
        // Without Optional.ofNullable the null checks have to be done manually before accessing anything that could throw a NullPointerException
        if (nullableBlogPost == null || nullableBlogPost.getAuthor() == null || nullableBlogPost.getAuthor().getJobTitle() == null) {
            return "Unknown";
        }
        // Once it's known that a NullPointerException won't be caused, the methods are called a second time to extract the values
        return nullableBlogPost.getAuthor().getJobTitle();
    }

    /**
     * Example of how Optional.empty is used to indicate a value is not populated
     *
     * @param nullableBlogPost input that may either be null, part-populated or fully-populated
     * @return The author's job title if available, otherwise Optional.empty()
     */
    public Optional<String> extractNullableJobTitleAsOptional(BlogPost nullableBlogPost) {
        // returns Optional.empty if nullableBlogPost is null
        return Optional.ofNullable(nullableBlogPost)
            // returns Optional.empty if blogPost.author is null
            .map(BlogPost::getAuthor)
            // returns Optional.empty if blogPost.author.jobTitle is null
            .map(Person::getJobTitle);
    }

    /**
     * Example of how null is used to indicate a value is not populated prior to Optional.empty
     *
     * @param nullableBlogPost input that may either be null, part-populated or fully-populated
     * @return The author's job title if available, otherwise null
     */
    public String extractNullableJobTitleAsNull(BlogPost nullableBlogPost) {
        // Without Optional.ofNullable the null checks have to be done manually before accessing anything that could throw a NullPointerException
        if (nullableBlogPost == null || nullableBlogPost.getAuthor() == null || nullableBlogPost.getAuthor().getJobTitle() == null) {
            return null;
        }
        return nullableBlogPost.getAuthor().getJobTitle();
    }

    /**
     * Example of how Optional.ofNullable is used to throw a custom exception when a value is not populated
     *
     * @param nullableBlogPost input that may either be null, part-populated or fully-populated
     * @return The author's job title if available
     * @throws CustomNotFoundException if the job title is null or cannot be extracted due to other objects being null
     */
    public String extractJobTitleOrExceptionUsingOptional(BlogPost nullableBlogPost) {
            // throws CustomNotFoundException if nullableBlogPost is null
        return Optional.ofNullable(nullableBlogPost)
            // throws CustomNotFoundException if blogPost.author is null
            .map(BlogPost::getAuthor)
            // throws CustomNotFoundException if blogPost.author.jobTitle is null
            .map(Person::getJobTitle)
            // orElseThrow uses NoSuchElementException by default but this shows how to use an alternative exception
            .orElseThrow(() -> new CustomNotFoundException("Cannot extract jobTitle from blog post"));
    }

    /**
     * Example of how a custom exception was thrown prior to Optional.ofNullable when a value is not populated
     *
     * @param nullableBlogPost input that may either be null, part-populated or fully-populated
     * @return The author's job title if available
     * @throws CustomNotFoundException if the job title is null or cannot be extracted due to other objects being null
     */
    public String extractJobTitleOrException(BlogPost nullableBlogPost) {
        // Without Optional.ofNullable the null checks have to be done manually before accessing anything that could throw a NullPointerException
        if (nullableBlogPost == null || nullableBlogPost.getAuthor() == null || nullableBlogPost.getAuthor().getJobTitle() == null) {
            throw new CustomNotFoundException("Cannot extract jobTitle from blog post");
        }
        return nullableBlogPost.getAuthor().getJobTitle();
    }
}
