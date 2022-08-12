package com.codewithamrit.myapplication.AdoptionQuestions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.codewithamrit.myapplication.GetterSetter.AdoptionQuestionModalClass;
import com.codewithamrit.myapplication.HandleDatabase.Database_Adoption;
import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class SlideViewPagerAdapter extends PagerAdapter {
    Context context;
    String dogId;
    ImageView btn_next,btn_previous,logo;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10;
    TextView question, mainPageText;
    String answer;
    public  String ans1 = null;
    public   String ans2=null;
    public   String ans3=null;
    public   String ans4=null;
    public   String ans5=null;
    public   String ans6=null;
    public   String ans7=null;
    public   String ans8=null;
    public   String ans9=null;
    public   String ans10=null;
    public SlideViewPagerAdapter(Context context, String dogId) {
        this.context = context;
        this.dogId=dogId;
    }

    @Override
    public int getCount() {
        return 11;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slide_screen,container,false);
        btn_next=view.findViewById(R.id.btn_next);
        btn_previous=view.findViewById(R.id.btn_previous);
        question=view.findViewById(R.id.question_container);
        logo=view.findViewById(R.id.adoption_image);
        mainPageText=view.findViewById(R.id.adoption_text);
        ImageView dot1=view.findViewById(R.id.dot1);
        ImageView dot2=view.findViewById(R.id.dot2);
        ImageView dot3=view.findViewById(R.id.dot3);
        ImageView dot4=view.findViewById(R.id.dot4);
        ImageView dot5=view.findViewById(R.id.dot5);
        ImageView dot6=view.findViewById(R.id.dot6);
        ImageView dot7=view.findViewById(R.id.dot7);
        ImageView dot8=view.findViewById(R.id.dot8);
        ImageView dot9=view.findViewById(R.id.dot9);
        ImageView dot10=view.findViewById(R.id.dot10);
        ImageView dot11=view.findViewById(R.id.dot11);
        Button start_btn=view.findViewById(R.id.next_to_question);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdoptionSlideActivity.viewPager.setCurrentItem(position+1);

            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdoptionSlideActivity.viewPager.setCurrentItem(position-1);
            }
        });
        AdoptionQuestionModalClass questionModalClass= new AdoptionQuestionModalClass();
        List<AdoptionQuestionModalClass> answer_list= new ArrayList<>();
        radioGroup= view.findViewById(R.id.radiogroup_ans);

        switch (position){
            case 0:
                question.setVisibility(View.GONE);
                radioGroup.setVisibility(View.GONE);
                btn_previous.setVisibility(View.GONE);
                dot2.setImageResource(R.drawable.unselected);
                dot3.setImageResource(R.drawable.unselected);
                dot4.setImageResource(R.drawable.unselected);
                dot5.setImageResource(R.drawable.unselected);
                dot6.setImageResource(R.drawable.unselected);
                dot7.setImageResource(R.drawable.unselected);
                dot8.setImageResource(R.drawable.unselected);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                start_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AdoptionSlideActivity.viewPager.setCurrentItem(1);

                    }
                });
                break;
            case 1:
                question.setText(Questions.question_first);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot3.setImageResource(R.drawable.unselected);
                dot4.setImageResource(R.drawable.unselected);
                dot5.setImageResource(R.drawable.unselected);
                dot6.setImageResource(R.drawable.unselected);
                dot7.setImageResource(R.drawable.unselected);
                dot8.setImageResource(R.drawable.unselected);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb1=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb1.getText().toString().trim();
                        rb1.setChecked(true);
                        ans1=answer;
                    }
                });
                break;
            case 2:
                question.setText(Questions.question_second);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot4.setImageResource(R.drawable.unselected);
                dot5.setImageResource(R.drawable.unselected);
                dot6.setImageResource(R.drawable.unselected);
                dot7.setImageResource(R.drawable.unselected);
                dot8.setImageResource(R.drawable.unselected);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb2=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb2.getText().toString().trim();
                       ans2=answer;
                       rb2.setChecked(true);
                    }
                });
                break;
            case 3:
                question.setText(Questions.question_third);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot5.setImageResource(R.drawable.unselected);
                dot6.setImageResource(R.drawable.unselected);
                dot7.setImageResource(R.drawable.unselected);
                dot8.setImageResource(R.drawable.unselected);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb3=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb3.getText().toString().trim();
                        ans3=answer;
                        rb3.setChecked(true);
                    }
                });
                break;
            case 4:
                question.setText(Questions.question_fourth);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot6.setImageResource(R.drawable.unselected);
                dot7.setImageResource(R.drawable.unselected);
                dot8.setImageResource(R.drawable.unselected);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb4=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb4.getText().toString().trim();
                        ans4=answer;
                        rb4.setChecked(true);
                    }
                });
                break;
            case 5:
                question.setText(Questions.question_fifth);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot7.setImageResource(R.drawable.unselected);
                dot8.setImageResource(R.drawable.unselected);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb5=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb5.getText().toString().trim();
                        ans5=answer;
                        rb5.setChecked(true);
                    }
                });
                break;
            case 6:
                question.setText(Questions.question_sixth);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot8.setImageResource(R.drawable.unselected);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb6=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb6.getText().toString().trim();
                        ans6=answer;
                        rb6.setChecked(true);
                    }
                });
                break;
            case 7:
                question.setText(Questions.question_seventh);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot9.setImageResource(R.drawable.unselected);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb7=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb7.getText().toString().trim();
                        ans7=answer;
                        rb7.setChecked(true);
                    }
                });
                break;
            case 8:
                question.setText(Questions.question_eighth);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot10.setImageResource(R.drawable.unselected);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb8=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb8.getText().toString().trim();
                       ans8=answer;
                        rb8.setChecked(true);
                    }
                });
                break;
            case 9:
                question.setText(Questions.question_ninth);
                logo.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                dot11.setImageResource(R.drawable.unselected);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb9=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb9.getText().toString().trim();
                        ans9=answer;
                        rb9.setChecked(true);
                    }
                });
                break;
            case 10:
                question.setText(Questions.question_ten);
                logo.setVisibility(View.GONE);
                btn_next.setVisibility(View.GONE);
                mainPageText.setVisibility(View.GONE);
                start_btn.setAlpha(0.3f);
                start_btn.setText("Finish");
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        rb10=view.findViewById(radioGroup.getCheckedRadioButtonId());
                        answer =rb10.getText().toString().trim();
                        ans10=answer;
                        rb10.setChecked(true);
                        start_btn.setAlpha(1);
                        start_btn.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                               if (ans1==null || ans2==null || ans3==null || ans4==null || ans5==null ||
                                       ans6==null || ans7==null || ans8==null || ans9==null || ans10==null){
                                   Toast.makeText(context, ""+"Please complete the form.", Toast.LENGTH_SHORT).show();

                               }
                               else{
                                   questionModalClass.setAns1(ans1);
                                   questionModalClass.setAns2(ans2);
                                   questionModalClass.setAns3(ans3);
                                   questionModalClass.setAns4(ans4);
                                   questionModalClass.setAns5(ans5);
                                   questionModalClass.setAns6(ans6);
                                   questionModalClass.setAns7(ans7);
                                   questionModalClass.setAns8(ans8);
                                   questionModalClass.setAns9(ans9);
                                   questionModalClass.setAns10(ans10);
                                   questionModalClass.setDogId(dogId);
                                   questionModalClass.setUserid( new SessionManager(context).getUserId());
                                   answer_list.add(questionModalClass);
//                                   Let's store to the database
                                   new Database_Adoption().insertAdoptionData(context,answer_list);
                               }

                            }
                        });
                    }
                });
                break;
        }

        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
