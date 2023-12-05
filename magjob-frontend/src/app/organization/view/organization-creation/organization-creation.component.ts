import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Organization} from "../../model/organization";
import { OrganizationCreationService } from '../../service/organization-creation.service';

@Component({
  selector: 'app-organization-creation',
  templateUrl: './organization-creation.component.html',
  styleUrls: ['./organization-creation.component.css']
})
export class OrganizationCreationComponent {

  organizationModel: Organization = new Organization('');

  organizationForm: FormGroup;
  selectedType: string = '';

  constructor(private organizationService: OrganizationCreationService, private fb: FormBuilder) {
    this.organizationForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
    const nameControl = this.organizationForm.get('name');

    if (nameControl && nameControl.valid) {
      this.organizationModel.name = nameControl.value;
      const type = this.selectedType;

      this.organizationService.createOrganization(this.organizationModel).subscribe(
          (response) => {
            console.log('Organization created successfully:', response);
          },
          (error) => {
            console.error('Error creating organization:', error);
          }
      );
    }
  }


  selectType(type: string) {
    this.selectedType = type;
  }
}
