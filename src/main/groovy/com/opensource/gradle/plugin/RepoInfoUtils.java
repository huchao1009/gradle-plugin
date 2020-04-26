package com.opensource.gradle.plugin;


public class RepoInfoUtils {
    public static String getRepoReleaseUrl() {
        return System.getenv("REPO_URL_RELEASE");
    }

    public static String getRepoSnapshotUrl() {
        return System.getenv("REPO_URL_SNAPSHOT");
    }

    public static String getRepoUserName() {
        return System.getenv("REPO_USER_NAME");
    }

    public static String getRepoPassword() {
        return System.getenv("REPO_PASSWORD");
    }
}
