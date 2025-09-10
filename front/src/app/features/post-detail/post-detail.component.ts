import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../posts/services/post.service';
import { CommentService, Comment } from '../posts/services/comment.service';
import { Post } from 'src/app/interfaces/post.interface';
import { Location } from '@angular/common';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss'],
})
export class PostDetailComponent implements OnInit {
  post: Post | null = null;
  comments: Comment[] = [];
  newComment: string = '';

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private location: Location,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.postService.getById(id).subscribe((data) => (this.post = data));
    this.loadComments(id);
  }

  loadComments(postId: number): void {
    this.commentService.getByPost(postId).subscribe((data) => {
      this.comments = data;
    });
  }
  public goBack(): void {
    this.location.back();
  }
  sendComment(): void {
    if (this.newComment.trim() && this.post) {
      const comment: Comment = {
        content: this.newComment,
        postId: this.post.id,
        userName: '',
      };

      this.commentService.create(comment).subscribe(() => {
        this.newComment = '';
        this.loadComments(this.post!.id);
      });
    }
  }
}
