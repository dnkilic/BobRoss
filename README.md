# BobRoss

BobRoss is an Android content loading library. Its able to load image, json and xml content while caching for efficient user experience.

<p align="center">
  <img src="/screenshots/bobross.jpeg" width="200" title="Bob Ross">
</p>

During the development of this library, I inspired from [Bob Ross](https://en.wikipedia.org/wiki/Bob_Ross) (October 29, 1942 – July 4, 1995) who was an American painter, art instructor, television host. He convinced many people how painting can be easy and joyful during his TV show [Joy of Painting](https://en.wikipedia.org/wiki/The_Joy_of_Painting). As he suggested, I tried to simplfy library interface as much as possible. Of course there might be some problems but as he said **"We don't make mistakes, we just have happy accidents."**.

As you can see from the [commit history](https://github.com/dnkilic/BobRoss/commits/master), sample app is implemented first. I used repository pattern to support offline mode. Following graph shows the starting architecture.

<p align="center">
  <img src="/screenshots/initial_arc.png" width="350" title="Initial Architecture">
</p>

I used **Retrofit2** to fetch api, **Room** to save posts into DB, **kotlin coroutines** to perform network and db actions from background and **Glide** to show images. Following screenshot shows the sample application after first phase is completed. 

<p align="center">
  <img src="/screenshots/phase1.png" width="200" title="First Phase">
</p>

During second phase BobRoss library is implemented. My main focus was simplicity of its interface. Library is able to fetch images, json and xml with memory caching functionality. I used Anko to perform background tasks. At the end of Phase 2, I replaced Retrofit and Glide with BobRoss. Following screenshot shows the sample application after second phase is completed. 

<p align="center">
  <img src="/screenshots/phase2.png" width="200" title="Second Phase">
</p>

During the third phase, I improved image loading capabilities by adding new features like supporting error placeholder. I added some extra features like xml fetching as well. 

<p align="center">
  <img src="/screenshots/phase3.png" width="200" title="Third Phase">
</p>
