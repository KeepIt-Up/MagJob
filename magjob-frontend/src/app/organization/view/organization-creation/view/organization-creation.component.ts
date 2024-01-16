import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrganizationCreation } from "../../../model/organization-creation";
import { OrganizationCreationService } from '../../organization-creation/service/organization-creation.service';

@Component({
  selector: 'app-organization-creation',
  templateUrl: './organization-creation.component.html',
  styleUrls: ['./organization-creation.component.css']
})
export class OrganizationCreationComponent {

  organizationModel: OrganizationCreation = {
    name: '',
    profileBannerUrl: '',
    user: 0
  };

  organizationForm: FormGroup;
  selectedBannerUrl: string = '';

  constructor(private organizationService: OrganizationCreationService, private fb: FormBuilder, private router: Router) {
    this.organizationForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
    const { name } = this.organizationForm.value;

    if (this.organizationForm.valid) {
      this.organizationModel.name = name;
      this.organizationModel.profileBannerUrl = ""; //this.selectedBannerUrl
      this.organizationModel.user = localStorage.getItem("User");


      this.organizationService.createOrganization(this.organizationModel).subscribe(
        (response) => {
          console.log('Organization created successfully:', response);
          this.router.navigate(['/organization/'+response.id+'/addMembers']);
        },
        (error) => {
          console.error('Error creating organization:', error);
        }
      );
    }
  }

  selectBannerUrl(url: string) {
    this.selectedBannerUrl = url;
  }
}
