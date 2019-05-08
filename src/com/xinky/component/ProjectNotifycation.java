package com.xinky.component;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

/**
 * @author zhangxinkun
 */
public class ProjectNotifycation implements ProjectComponent {

  private Project project;

  public ProjectNotifycation(Project project) {
    this.project = project;
  }

  private final String KEY_LASTNOTIFY = "com.xinky.component.materialdesign.theme.lastnotify";

  @Override
  public void projectOpened() {
    long lastNotify = PropertiesComponent.getInstance(project).getOrInitLong(KEY_LASTNOTIFY, 0);
    if (DateUtils.isSameDay(new Date(), new Date(lastNotify))) {
      return;
    }
    final Notification notification = new Notification(
        "Material Design Dark-Theme",
        "Material Design Dark-Theme",
        "<html>If you love my theme plugin, please vote on <a href=\"https://plugins.jetbrains.com/contest/intellij-themes/2019\">IntelliJ Themes Contest 2019</a>. <br/>Really thanks!<html>",
        NotificationType.INFORMATION, NotificationListener.URL_OPENING_LISTENER);
    notification.notify(project);
    PropertiesComponent.getInstance(project).setValue(KEY_LASTNOTIFY, new Date().getTime() + "");
  }
}
