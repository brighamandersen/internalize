# Internalize

![App banner](./non_code_icons/internalize-canva-banner.jpg)

A Native Android app to help you memorize passages using fill in the blanks!

This app was actually originally written in Flutter as I thought I needed a cross-platform solution, but then after I saw that at least for the MVP the scope would be more for my own use while I ironed a few things out with the user experience, I decided I'd rather build it in Jetpack Compose and have even better performance, as well as building my skills in native Android which aligned more closely with my goals. See https://github.com/brighamandersen/internalize-flutter for more details on my learning journey on the Flutter side of things if you're curious.

## Launcher/Store App Icons

You can find the master icon used to generate all the iOS/Android variations in `/non_code_icons/`. There you can also find the app icons used for the iOS App Store and the Google Play Store. All these icons in this folder are not actually used in the code, I just keep them in the repo so they're easy to find. As for the icons that are generated from the icon and used directly in the app in `android/app/src/main/res/mipmap*`.

## Decisions

- Material 2 instead of Material 3 for UI
  - Material 3 is the future but I quickly ran into cases where experimental flags would have to be used so opted for the tried and true Material 2 for now.
- Jetpack Compose instead of XML
  - In all my research Jetpack Compose seems to be the future of Android UI development. In my experience it's more React-like and easier to use as well.
- One single activity
  - Seems like this is the recommendation when using Jetpack Compose since activities are expensive to open and close. It's worked well so far.
- JSON for persisting data and then Kotlin in-memory models.
  - I knew I would want the Kotlin in-memory models to keep everything super fluid between interactions. Ideally I would have persisted data using Room as that's the industry standard and scalable solution, but this was a project that I was hoping to finish a while back and I don't think users will have enough passages to even lead to performance issues that JSON can have, so it's a fine solution for now.

## Testing

The app is small enough in scope that I won't bother with unit or integration tests. To simplify the project structure I just removed all the testing dependencies and the example test files.

If things change and testing needs to be added back in, look through the git commit history for a commit like [Remove testing dependencies](https://github.com/brighamandersen/internalize/commit/b1a0b05822d035814a49d7c0a66cc9940678c870) to see which dependencies to add back. Also note that you'll need to recreate test directories in `app/src/test` and `app/src/androidTest`.
