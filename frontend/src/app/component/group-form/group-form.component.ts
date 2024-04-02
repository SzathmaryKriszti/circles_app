import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CirclesService} from "../../services/circles.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-group-form',
  templateUrl: './group-form.component.html',
  styleUrls: ['./group-form.component.css']
})
export class GroupFormComponent implements OnInit {

  groupForm!: FormGroup;
  groupId!: number;

  constructor(private formBuilder: FormBuilder,
              private circlesService: CirclesService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

    this.groupForm = formBuilder.group({
      groupName: ['', Validators.required],
      imgUrl: ['']
    })

  }

  ngOnInit(): void {
  }

  onSubmit() {
    const data = {...this.groupForm.value}
    this.circlesService.createGroup(data).subscribe({
      next: () => this.router.navigate(['/my-groups']),
      error: (err: any) => console.error(err)
    })
  }

}
