# Pusher client events between Android & Web app

Sample project using [Pusher](https://pusher.com/) for communicating between an Android app & a web application

To be able to use client events, a server part is necessary for serving the [authorization endpoint](https://pusher.com/docs/channels/using_channels/events#triggering-client-events)

Also check "Enable client events" :

![client-event](https://user-images.githubusercontent.com/5183022/59983827-1eb9e880-9624-11e9-9aee-7f3bc8bbce7d.png)

## Architecture : 

* Android application
* Web application
* NodeJS server serving the authorization endpoint (on the same domain as the web application above)

## Configuration

Change the configuration under the following path :

* Android : [strings.xml](https://github.com/bertrandmartel/pusher-client-events/blob/master/android/app/src/main/res/values/strings.xml)
* Web / server : [app.js](https://github.com/bertrandmartel/pusher-client-events/blob/master/web/app.js) 