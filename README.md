# Coding Challenge Android

## The App

This app hits https://api.stackexchange.com/2.2/users?site=stackoverflow and retrieve the first page of data and displays the users name, reputation points, badges, and picture. When clicking on the item it will open to that users page on stack overflow in a chrome custom tab. The items are displayed in a recyclerview grid of cardviews. This was done using an MVVM pattern with using AndroidViewModel. I wrote this app in Kotlin because I believe that it is a great step in the right direction for Android as a whole. Writing in Kotlin saves a lot of time and effort when writing Android apps. It helps save a lot of the boilerplate code that must be written in Java and provides more functional programming. One example would get Kotlin extentions or synthetic properties so we never have to write findViewById or inject view again.

## Third-party libraries

- Retrofit - This library is easy to setup in an a project and  makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. It allows easy mappings for json/data via a configurable converter to your data models. It uses a great set of annotations to help reduce boilerplate and enforce rules.

- RxJava/RxAndroid - This library comes with a bit of a learning curve but makes your life easier in the end. RxJava is used for reactive programming. In reactive programming, the consumer reacts to the data as it comes in. Reactive programming allows for event changes to propagate to registered observers. 

- Glide - This supports fetching, decoding, and displaying video stills, images, and animated GIFs. Glide includes a flexible API that allows developers to plug in to almost any network stack. Glide is also effective for almost any case where you need to fetch, resize, and display a remote image. One of the requirements was that each photo should only be downloaded once and stored for offline usage. By settings Glides disk cache strategry we achieve this without using some sort of persistant storage. Glide checks multiple layers of caches before starting a new request for an image:
        - Active resources - Is this image displayed in another View right now?
        - Memory cache - Was this image recently loaded and still in memory?
        - Resource - Has this image been decoded, transformed, and written to the disk cache before?
        - Data - Was the data this image was obtained from written to the disk cache before?
The first two steps check to see if the resource is in memory and if so, return the image immediately. The second two steps check to see if the image is on disk and return quickly, but asynchronously and if those fail it makes the call again.

- Dagger 2 - Dagger is a fully static, compile-time dependency injection framework for both Java and Android. It is an adaptation of an earlier version created by Square and now maintained by Google. This also uses annotations to that help keep boilerplate to a minimum while enforcing strict rules.



![image](https://user-images.githubusercontent.com/5893051/36951110-6546154a-1fb4-11e8-8f92-63c542debb45.png)
