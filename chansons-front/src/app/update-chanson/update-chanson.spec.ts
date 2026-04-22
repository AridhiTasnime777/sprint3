import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateChanson } from './update-chanson';

describe('UpdateChanson', () => {
  let component: UpdateChanson;
  let fixture: ComponentFixture<UpdateChanson>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateChanson],
    }).compileComponents();

    fixture = TestBed.createComponent(UpdateChanson);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
