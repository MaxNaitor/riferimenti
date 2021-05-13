import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  baseURL = "https://corso-angular-60c26-default-rtdb.europe-west1.firebasedatabase.app/"
  loadedPosts = [];
  isLoading = false;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.onFetchPosts()
  }

  onCreatePost(postData: { title: string; content: string }) {
    // Send Http request
    this.http.post(this.baseURL + 'posts.json', postData).subscribe(responseData => {
      console.log(responseData)
    })
  }

  onFetchPosts() {
    this.isLoading = true;
    this.http.get(this.baseURL + 'posts.json')
      .pipe(map(responseData => {
        const postArray = []
        for (const key in responseData) {
          if (responseData.hasOwnProperty(key)) {
            postArray.push({ ...responseData[key], id: key })
          }
        }
        return postArray
      }))
      .subscribe(responseData => {
        this.loadedPosts = responseData
        this.isLoading = false;
      })
  }

  onClearPosts() {
    // Send Http request
  }
}
