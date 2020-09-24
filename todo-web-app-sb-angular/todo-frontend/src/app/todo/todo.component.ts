import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../list-todos/list-todos.component';
import { TodoDataService } from '../service/data/todo-data.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  id: number;
  todo:Todo;

  constructor(
    private todoService: TodoDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.todo = new Todo(this.id,'', false, new Date());

    if(this.id != -1) {
      this.todoService.retrieveTodo('arifmondal', this.id)
        .subscribe(
          (response) => {
            this.todo = response;
          },
          (err) => {
            console.log(err);
          }
        );
    }
  }

  saveTodo() {
    console.log(this.todo);
    if(this.id === -1) {
      // create todo
      this.todoService.createTodo('arifmondal', this.todo)
        .subscribe(
          (response) => {
            console.log(response);
            this.router.navigate(['todos']);
          },
          (err) => {
            console.log(err);
          }
        );
    } else {
      // update todo
      this.todoService.updateTodo('arifmondal', this.id, this.todo)
        .subscribe(
          (response) => {
            console.log(response);
            this.router.navigate(['todos']);
          },
          (err) => {
            console.log(err);
          }
        );
    }
  }
}
