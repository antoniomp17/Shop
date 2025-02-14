
# Shop Challenge

This project is a mobile app that allows users to browse a list of products, add them to their cart, and proceed to checkout with applied discounts. Built with production-ready standards, the app fetches product data from a remote source using Retrofit and uses Room for local data storage. The architecture follows Clean principles, structured with Jetpack Compose for a modern UI experience. Discounts, such as "2-for-1" and bulk pricing, are calculated dynamically to provide users with transparent pricing information. Basic unit and UI testing ensure reliability, with MutableStateFlow handling state management effectively.


## Features

- *Dynamic Product Listing with Discounts*
The app provides an intuitive, user-friendly product list with dynamically applied discounts. The product list is fetched using Retrofit, displayed in a responsive LazyColumn that adapts to any number of items, and images are loaded with Glide for seamless performance.

- *Search and Voice Input*
The top bar of the product list screen includes a search bar to quickly find any desired product. A microphone button enables voice input, converting spoken words to text for ease of use. The top bar also displays a cart icon showing the total items in the cart and the cumulative price.

- *Discount Highlighting and Descriptions*
Items with active discounts are highlighted with Shop’s signature purple tone and marked with a discount type notation. Tapping on a discounted item opens a dialog displaying detailed information about the discount.

- *Cart Overview*
In the cart screen, users can view the items selected for purchase, each listed in an adaptive LazyColumn with the same functionalities as the product list screen. Here, each item's accumulated total price is displayed.

- *Total Price Summary*
A bottom bar presents two fields showing the total price without discounts and the total price after discounts.

- *Quick Return to Product List*
A floating action button allows users to quickly return to the product listing screen from the cart screen.

- *Data Persistence with Room*
Thanks to data persistence using Room, users can exit and re-enter the app without losing the items stored in their cart, providing a seamless shopping experience.

- *Error Messaging*
The app includes error messages to inform users of any connectivity issues or similar problems, ensuring a better user experience even when challenges arise.
## Architecture
The app follows Clean Architecture, organizing the core into four main layers: data, domain, presentation, and utils.

- *Data Layer*
This layer manages the product API using Retrofit, the local database with Room, and dependency injection with Hilt.

- *Domain Layer*
Here, we handle the business logic for the cart and discounts, along with typical domain logic, including use cases.

- *Presentation Layer*
This layer contains all the composables and screens, as well as the main ViewModel that connects the UI with the business logic.

- *Utils*
A dedicated folder for utility functions that assist in various aspects of development, enhancing code reusability and organization.
## Frequently Asked Questions

- *Why did you use Room instead of DataStore?*
While it may have been preferable to use DataStore since we only need to store the product codes in the cart, I chose to use Room for this project. I believe it better showcases my skills in handling local databases, making it more interesting and relevant for a challenge of this nature.

- *Why did you use MutableStateFlow instead of mutableStateOf?*
I chose to use MutableStateFlow over mutableStateOf because MutableStateFlow is part of Kotlin's Flow API, which provides better handling of state in a reactive manner. It allows for more powerful state management, including the ability to collect and observe state changes across multiple components. This is particularly useful for managing complex UI states and ensuring that updates are efficiently propagated, making the app more responsive and easier to maintain. Additionally, StateFlow supports lifecycle awareness, which helps avoid memory leaks and ensures proper resource management.


- *Why did you implement that amount of unit and instrumental tests?*
I aimed to cover a range of simple tests that effectively demonstrate my understanding of testing principles. While I certainly could have delved into more complex testing scenarios, I felt that for this challenge, the requirements did not necessitate that level of complexity. My focus was on ensuring that the core functionality is well-tested and reliable, providing a solid foundation for the application while also showcasing my skills in a practical manner.

- *Why did you choose Jetpack Compose for the user interface?*
I chose Jetpack Compose for several reasons. First, it simplifies the process of creating user interfaces by allowing for a declarative approach, which makes it easier to visualize the UI as a function of the current state. This reactivity helps in managing state changes efficiently, leading to a more responsive user experience. Additionally, Jetpack Compose reduces boilerplate code and integrates seamlessly with other Jetpack components, allowing for faster development and easier maintenance. Overall, it enhances the development experience by providing powerful tools for building modern Android UIs.

- *How did you handle dependency management in the project?*
I utilized Hilt for dependency injection, which significantly contributes to maintaining clean and organized code. Hilt simplifies the process of managing dependencies by providing a framework that handles the instantiation of classes and their dependencies automatically. This reduces the need for manual setup and boilerplate code, enabling a more modular design. By leveraging Hilt, I can ensure that components are properly scoped and lifecycled, leading to improved testability and easier maintenance of the application.

- *How could you expand or improve the application in the future?*
In the future, I would enhance the application by adding discount information directly into the product JSON, decoupling this responsibility from the app itself. This would allow for greater flexibility and easier updates to discount logic without modifying the app code. Additionally, I would consider creating a payment processing platform and implementing user management features, enabling a more comprehensive shopping experience for users. This could include functionalities like user authentication, order history, and personalized product recommendations.
## Additional Information

This project is developed using Kotlin version 2.0.21 and was programmed in Android Studio Ladybug.

For any further questions or inquiries, please feel free to reach out!

Contact: antoniomoraleda17@gmail.com