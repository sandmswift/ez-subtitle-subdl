### 1.Description
This project uses the official API of this website for subtitle search and download.Search the website for [https://subdl.com.](https://subdl.com.)
You can query specific interface data details through the official api documentation:[https://subdl.com/api-doc](https://subdl.com/api-doc)
### 2.Introduction to use
#### 2.1Project compilation environment introduction

- **Android Studio**

   ![image.png](https://cdn.nlark.com/yuque/0/2024/png/26033890/1714008136779-07b50893-a176-4bde-96f8-3fde3e0abe62.png#averageHue=%230e0b07&clientId=ud01d96d7-db88-4&from=paste&height=226&id=epcTF&originHeight=296&originWidth=549&originalType=binary&ratio=1&rotation=0&showTitle=false&size=18864&status=done&style=none&taskId=u2c7ebc2f-694d-44b8-8b37-4e4b4d1a332&title=&width=420)

- **Android Gradle Plugin Version : 4.2.2**
- **Gradle Version: 6.8.3**
- **Gradle JDK: corretto-11 Amazon Corretto version11**

   ![image.png](https://cdn.nlark.com/yuque/0/2024/png/26033890/1714008419320-2871902c-8b78-468a-8b45-2ff5e373cd6d.png#averageHue=%23090705&clientId=ud01d96d7-db88-4&from=paste&height=262&id=u910637f0&originHeight=507&originWidth=959&originalType=binary&ratio=1&rotation=0&showTitle=false&size=29428&status=done&style=none&taskId=uc383d261-9952-4943-adc9-6e8440fd386&title=&width=495)
   ![image.png](https://cdn.nlark.com/yuque/0/2024/png/26033890/1714030155379-3e44883c-9258-40d6-8cfd-10e48b713748.png#averageHue=%230b0907&clientId=u35d355fa-bb34-4&from=paste&height=202&id=nhaxq&originHeight=202&originWidth=483&originalType=binary&ratio=1&rotation=0&showTitle=false&size=10755&status=done&style=none&taskId=ud2af71ab-4718-4d40-9b20-d970898fe74&title=&width=483)
Many times the compilation environment will appear dependency compatibility issues, if your environment is not compatible can refer to my compilation environment.
#### 2.2 how to run introduction
##### replace the api key from subdl.com
first you need to go to the [https://subdl.com](https://subdl.com.) and complete the registration login, and then get your api key
![image.png](https://cdn.nlark.com/yuque/0/2024/png/26033890/1714007711208-a5ba7da1-1c3b-4d9d-9032-ef2f5026d511.png#averageHue=%23ece472&clientId=ud01d96d7-db88-4&from=paste&height=832&id=u836fb5ed&originHeight=832&originWidth=1409&originalType=binary&ratio=1&rotation=0&showTitle=false&size=229678&status=done&style=none&taskId=ubfba4d2a-ba80-41d5-b3ee-93c2bff093a&title=&width=1409)
then replace the api key value in the project
![image.png](https://cdn.nlark.com/yuque/0/2024/png/26033890/1714008816268-fe840e1d-9336-4af2-a635-e3eada8b2aa9.png#averageHue=%23313f2f&clientId=ud01d96d7-db88-4&from=paste&height=278&id=ued21c0ba&originHeight=653&originWidth=1484&originalType=binary&ratio=1&rotation=0&showTitle=false&size=842007&status=done&style=none&taskId=u857389fd-8586-417f-adb7-1b6e0e417e5&title=&width=632)
#### Test if the api is available
Replace the your-api-key in the following URL, and then access it from your browser. If you can get the following format correctly, the interface is working
example:
https://api.subdl.com/api/v1/subtitles?api_key=your-api-key&film_name=knox goes away&type=movie&languages=En
```graphql
{
    "status": true,
    "results": [
        {
            "sd_id": 1671422,
            "type": "movie",
            "name": "Knox Goes Away",
            "imdb_id": "tt20115766",
            "tmdb_id": 972614,
            "first_air_date": null,
            "year": null
        }
    ],
    "subtitles": [
        {
            "release_name": "Knox.Goes.Away.2023.WEB.H264-RBB",
            "name": "SUBDL::knox-goes-away-english-3318092.zip",
            "lang": "english",
            "author": "sash35",
            "url": "/subtitle/3295658-3318092.zip",
            "season": 0,
            "episode": 0
        },
        {
            "release_name": "Knox.Goes.Away.2023.WEB.H264-RBB",
            "name": "SUBDL::knox-goes-away-english-3318093.zip",
            "lang": "english",
            "author": "sash35",
            "url": "/subtitle/3295657-3318093.zip",
            "season": 0,
            "episode": 0
        },
        {
            "release_name": "Knox.Goes.Away.2023.1080p.AMZN.WEB-DL.DDP5.1.H.264",
            "name": "SUBDL::knox-goes-away-english-3308225.zip",
            "lang": "english",
            "author": "VikramJS",
            "url": "/subtitle/3286182-3308225.zip",
            "season": 0,
            "episode": 0
        },
        {
            "release_name": "Knox.Goes.Away.2023.1080p.AMZN.WEB-DL.DDP5.1.H.264",
            "name": "SUBDL::knox-goes-away-english-3308226.zip",
            "lang": "english",
            "author": "VikramJS",
            "url": "/subtitle/3286181-3308226.zip",
            "season": 0,
            "episode": 0
        }
    ]
}
```
In addition, you can test it on the default page of the app search results, I have done the search results packaging processing to check that the item search function is working
![image.png](https://cdn.nlark.com/yuque/0/2024/png/26033890/1714009823554-973aacdd-f459-4629-b9ab-3153f3daa7da.png#averageHue=%23393939&clientId=ud01d96d7-db88-4&from=paste&height=622&id=ubcdb468c&originHeight=840&originWidth=444&originalType=binary&ratio=1&rotation=0&showTitle=false&size=144910&status=done&style=none&taskId=ubf73e6fa-1b88-4de3-8fdc-4212f08abb9&title=&width=329)

Finally,if you have any questions need timely reply, you can contact me by email:1732095688@qq.com
