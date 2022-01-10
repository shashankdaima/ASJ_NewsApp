# Android-Study-Jams
## ASJ_NewsApp

### **Problem Statement:**
Getting daily news on important matters has been something practiced by people for a very long time. But as the world started modernizing and technology progressed, people have started looking for mobile browser to get daily news. It may or maynot provide complete picture of certain agendas. Most of the news are usually support one particular political parties and present skewed image of agenda. 
Proposed Solution :
We have developed a news-fetching app using simple and basic UI, so that everyone can understand and use it easily. Its features include saving new news along with a description given by multiple news websites. Once you are done reading it, it can be deleted to remove it from the application. All news are shown on the main screen and we can scroll through to find our required news page.

### **Screenshots:**
<img src="https://github.com/shashankdaima/ASJ_NewsApp/blob/master/latest_news.jpeg" alt="Latest News" width =250px>
<img src="https://github.com/shashankdaima/ASJ_NewsApp/blob/master/saved_news.jpeg" alt="Saved news Screen" width =250px>
<img src="https://github.com/shashankdaima/ASJ_NewsApp/blob/master/delete_new_with_snackbar.jpeg" alt="Web view" width =250px>

### **Functionality & Concepts used:**
1.	LiveData and Kotlin Flows
2.	ViewBinding 
3.	Room Database 
4.	Navigation Library 
5.	Retrofit 
6. Paging-3 for pagination

The App has a very simple and interactive interface which helps the users efficiently save and manage their news. Following are few android concepts used to achieve the functionalities in app :
*	Constraint Layout : Most of the small views(like item view of recycler view) in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
*	Coordinator Layout : Most of fragments have coordinator layout which make it easy to make snack bar and some other common functionality to Fragment.
*	RecyclerView : RecyclerView with ListAdapter is used to present all the news 
*	LiveData & Room Database : New saved news can be added to the application an any instant. LiveData is used to update and observe any changes made in the news articles, and update it to local databases using Room. 

### **Application Link & Future Scope:**
The app is currently in the Alpha testing phase with limited number of users. Access to the app : https://github.com/shashankdaima/ASJ_NewsApp/tree/master

Once the app is fully tested and functional  with the existing feature, we are planning to modify the application and add more features like: 
* Using work manager we are make a api calls and notify user about latest news. This help them to get reminded about check news on daily basis. 
*	adding MP-Charts and tracking user activity. So, that one can check his/her progress.
*	Adding dark mode/night mode

