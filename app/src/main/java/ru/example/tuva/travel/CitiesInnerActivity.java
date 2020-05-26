package ru.example.tuva.travel;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import ru.example.tuva.travel.Adapters.CommentAdapter;
import ru.example.tuva.travel.Adapters.ExperiencesViewAdapter;
import ru.example.tuva.travel.Adapters.NavButtonsViewAdapter;
import ru.example.tuva.travel.Model.Comment;
import ru.example.tuva.travel.Model.Experiences;
import ru.example.tuva.travel.Model.NavButtons;
import ru.example.tuva.travel.Prevalent.Prevalent;


public class CitiesInnerActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private EditText editTextComment;
    private Button btnAddComment;
    private RecyclerView RvComment;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comment" ;
    ImageView imgCurrentUser;
    ImageButton deleteBtn;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseUser user;
    private DatabaseReference reference, userInfoDatabase;
    private FirebaseAuth.AuthStateListener stateListener;
    private String firebaseId;
    private Object DatabaseReference;

    private static final String TAG = "CitiesActivity";
    ExpandableTextView expandableTextView;
    ImageButton imageButton;
    RecyclerView expRecyclerView;
    ExperiencesViewAdapter expAdapter;
    List<Experiences> experiencesList;

  RecyclerView navBtnRV;
    NavButtonsViewAdapter navButtonsViewAdapter;
    List<NavButtons> navButtonsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_inner_view);
        Log.d(TAG, "onCreate: started.");

        editTextComment = findViewById(R.id.post_detail_comment);
        btnAddComment = findViewById(R.id.post_detail_add_comment_btn);
        imgCurrentUser = findViewById(R.id.post_detail_currentuser_img);
        RvComment = findViewById(R.id.rv_comment);
        firebaseId = Prevalent.currentOnlineUser.getPhone();
        deleteBtn = findViewById(R.id.delete_img);

        expRecyclerView = findViewById(R.id.experience_recyclerView);
        expRecyclerView.setHasFixedSize(true);
        expRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        getIncomingIntent();
        expAdapter = new ExperiencesViewAdapter(this, experiencesList);
        expRecyclerView.setAdapter(expAdapter);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        userInfoDatabase = database.getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        editTextComment = findViewById(R.id.post_detail_comment);
        btnAddComment = findViewById(R.id.post_detail_add_comment_btn);
        imgCurrentUser = findViewById(R.id.post_detail_currentuser_img);
        RvComment = findViewById(R.id.rv_comment);

        Bundle bundle = getIntent().getExtras();
        final String cityTitle = bundle.getString("city_title");
        TextView cTitle = findViewById(R.id.city_title);
        cTitle.setText(cityTitle);


        firebaseDatabase = FirebaseDatabase.getInstance();

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnAddComment.setVisibility(View.INVISIBLE);// комментирование
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(cityTitle).child(firebaseId);
                String comment_content = editTextComment.getText().toString();
                String uid = firebaseId;
                String uname = Prevalent.currentOnlineUser.getName();
                String uimg = Prevalent.currentOnlineUser.getImage();
                Comment comment = new Comment(comment_content, uid, uimg, uname);

                if (!(comment_content.equals(""))) {

                    commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            showMessage("Отправлено");
                            editTextComment.setText("");
                            btnAddComment.setVisibility(View.VISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showMessage("fail to add comment : " + e.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(CitiesInnerActivity.this, "Пустое сообщение", Toast.LENGTH_SHORT).show();
                    btnAddComment.setVisibility(View.VISIBLE);
                }
            }
        });


        iniRvComment();

//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                DatabaseReference databaseReference = firebaseDatabase.getReference(COMMENT_KEY).child(cityTitle).child(firebaseId);
//                String comment_content = editTextComment.getText().toString();
//                String uid = firebaseId;
//                String uname = Prevalent.currentOnlineUser.getName();
//                String uimg = Prevalent.currentOnlineUser.getImage();
//                Comment comment = new Comment(comment_content, uid, uimg, uname);
//
//                    databaseReference.setValue("").addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            showMessage("Удалено");
//                            editTextComment.setText("");
//                            btnAddComment.setVisibility(View.VISIBLE);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            showMessage("fail to delete comment : " + e.getMessage());
//                        }
//                    });
//            }
//        });

    }

    private void iniRvComment() {
        RvComment.setLayoutManager(new LinearLayoutManager(CitiesInnerActivity.this));

        Bundle bundle = getIntent().getExtras();
        final String cityTitle = bundle.getString("city_title");
        TextView cTitle = findViewById(R.id.city_title);
        cTitle.setText(cityTitle);

        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(cityTitle);
        commentRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listComment = new ArrayList<>();
                for (DataSnapshot snap:dataSnapshot.getChildren()) {
                    Comment comment = snap.getValue(Comment.class);
                    listComment.add(comment);
                }
                commentAdapter = new CommentAdapter(getApplicationContext(),listComment);
                RvComment.setAdapter(commentAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            int cityBg = bundle.getInt("city_bg");
            String cityTitle = bundle.getString("city_title");
            String cityDesc = bundle.getString("city_desc");
            //NavButtons
            navBtnRV = findViewById(R.id.nav_button_recyclerView);
            navBtnRV.setHasFixedSize(true);
            navBtnRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            assert cityTitle != null;
            if(cityTitle.equals("Торе-Холь")){ Torehol(); }
            if(cityTitle.equals("Азас")){ Azas(); }
            if(cityTitle.equals("Ногаан Хол")){ Nogaanhol(); }
            if(cityTitle.equals("Алдын-Булак")){ AldynBulak(); }
            navButtonsViewAdapter = new NavButtonsViewAdapter(this, navButtonsList);
            navBtnRV.setAdapter(navButtonsViewAdapter);
            setImage(cityBg, cityTitle, cityDesc);
        }
    }

    private void setImage(int city_Bg, String cityTitle, String cityDesc){
        Log.d(TAG, "setImage: setting image and name to widgets.");

        ImageView cBG = findViewById(R.id.city_image);
        cBG.setImageResource(city_Bg);
        cBG.setColorFilter(0xffAEAEAE, PorterDuff.Mode.MULTIPLY);


        TextView cTitle = findViewById(R.id.city_title);
        cTitle.setText(cityTitle);

        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(cityDesc);

        imageButton = findViewById(R.id.expand_collapse);
        imageButton.setColorFilter(0xff78849E, PorterDuff.Mode.SRC_ATOP);
    }

    private void Torehol(){
        experiencesList = new ArrayList<>();
        final List<Experiences> exp_list = experiencesList;
        exp_list.add(
                new Experiences(
                        1,
                        R.drawable.dos1));
        exp_list.add(
                new Experiences(
                        1,
                        R.drawable.dos2));
        exp_list.add(
                new Experiences(
                        1,
                        R.drawable.dos3));
        exp_list.add(
                new Experiences(
                        1,
                        R.drawable.dos4));
        exp_list.add(
                new Experiences(
                        1,
                        R.drawable.dos5));

        //NavButtons
        navButtonsList = new ArrayList<>();
        List<NavButtons> navButtons = navButtonsList;
        navButtons.add(
                new NavButtons(1,
                        R.drawable.paxtagulplate,
                        "Базы отдыха",
                        "Torehol",
                        R.drawable.tore));
        navButtons.add(
                new NavButtons(1,
                        R.drawable.map,
                        "Торе-Холь",
                        "Torehol",
                        R.drawable.tore));
    }


    private void Azas(){ experiencesList = new ArrayList<>();
        //NavButtons
        navButtonsList = new ArrayList<>();
        List<NavButtons> navButtons = navButtonsList;
        navButtons.add(
                new NavButtons(1,
                        R.drawable.paxtagulplate,
                        "Базы отдыха",
                        "Азас",
                        R.drawable.azas));
        navButtons.add(
                new NavButtons(1,
                        R.drawable.map,
                        "Карта",
                        "Азас",
                        R.drawable.azas));
    }


    private void Nogaanhol(){ experiencesList = new ArrayList<>();
        //NavButtons
        navButtonsList = new ArrayList<>();
        List<NavButtons> navButtons = navButtonsList;
        navButtons.add(
                new NavButtons(1,
                        R.drawable.paxtagulplate,
                        "Базы отдыха",
                        "В Ногаан-Хол",
                        R.drawable.fon));
        navButtons.add(
                new NavButtons(1,
                        R.drawable.map,
                        "Карта",
                        "В Ногаан-Хол",
                        R.drawable.fon));
    }

     private void AldynBulak(){
        experiencesList = new ArrayList<>();
        //NavButtons
        navButtonsList = new ArrayList<>();
        List<NavButtons> navButtons = navButtonsList;
        navButtons.add(
                new NavButtons(1,
                        R.drawable.paxtagulplate,
                        "Базы отдыха",
                        "В Алдын-Булак",
                        R.drawable.ald));
        navButtons.add(
                new NavButtons(1,
                        R.drawable.map,
                        "Карта",
                        "В Алдын-Булак",
                        R.drawable.ald));


    }

}
