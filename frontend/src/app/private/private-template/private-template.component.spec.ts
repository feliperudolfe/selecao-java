import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivateTemplateComponent } from './private-template.component';

describe('PrivateTemplateComponent', () => {
  let component: PrivateTemplateComponent;
  let fixture: ComponentFixture<PrivateTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrivateTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivateTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
