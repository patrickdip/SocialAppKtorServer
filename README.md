# Social Media App - Ktor Server (Work in Progress) 🚧

This repository contains the backend server code for the Social Media App [here](https://github.com/patrickdip/KMM_SocialMediaApp) built using Ktor.


## Getting Started 🚀

### Prerequisites
- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)

### Installation
1. Clone this repository.
2. Build the project using Gradle.
3. Run the server locally using `./gradlew run`.

   

## Usage ℹ️

### API Endpoints implemented as for now
- `/signup`: Creates a new account.
- `/signup`: Logins to an existing account
- `/follow`: Adds and removes a follower (following).
- `/post/create`: Creates a new Post
- `/post/{postId}` Returns the Post associated with this `postId` when it's a `GET` request. Deletes the Post when it's a `DELETE` request.
- `/posts/feed`: Returns paginated Posts from people you follow. Query parameters (currentUserId, page, limit)
- `/posts/{userId}`: Returns paginated Posts of this `userId`. Query parameters (currentUserId, page, limit)

## Contributing 🤝

**Please Note:** Contributions to this project will be welcome after the completion of our [YouTube video series](https://www.youtube.com/watch?v=_Kpti1tXgfc&list=PL2OhfKAEqtl99uxJMCKFM7XbcRmEQVyhW&ab_channel=MrDipCoding). Thank you for your interest!



## Video Series ▶️

Check out our [YouTube video series](https://www.youtube.com/watch?v=_Kpti1tXgfc&list=PL2OhfKAEqtl99uxJMCKFM7XbcRmEQVyhW&ab_channel=MrDipCoding) that walks through the development of this Social Media App using Ktor for backend and Kotlin Multiplatform for mobile client apps.



## Client Repository 📱

The client side of this Social Media App is built using Kotlin Multiplatform. Find the client repository [here](https://github.com/patrickdip/KMM_SocialMediaApp).
