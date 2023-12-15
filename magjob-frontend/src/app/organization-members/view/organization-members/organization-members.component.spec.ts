import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationMembersComponent } from '../organization-members/organization-members.component';

describe('OrganizationMembersComponent', () => {
  let component: OrganizationMembersComponent;
  let fixture: ComponentFixture<OrganizationMembersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrganizationMembersComponent]
    });
    fixture = TestBed.createComponent(OrganizationMembersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
