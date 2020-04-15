package com.example.newinsiteaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PopularTours extends AppCompatActivity {
TextView tour1,tour2,tour3,places;
DatabaseReference databaseReference;
    ArrayList<PoularTourModel>poularTourModels;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_tours);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Popular Tours");

        recyclerView=findViewById(R.id.recycleViewTour);
        tour1=findViewById(R.id.tour1);
        tour2=findViewById(R.id.tour2);
        tour3=findViewById(R.id.tour3);
        places=findViewById(R.id.places);

        poularTourModels=new ArrayList<>();

        poularTourModels.add(new PoularTourModel(R.drawable.image13,"1 Rajamalai (Eravikulam) National Park","2 Tea Gardens","3 Kolukkumalai Tea Estate","Munnar","Sprawling tea plantations surround the serene hills of Munnar, which attract adventure travelers hungry for paragliding, treks to Anaimudi (South India’s highest peak) and hikes originating at the confluence of three mountain streams."));
        poularTourModels.add(new PoularTourModel(R.drawable.image15,"1 Manikaran Gurudwara","2 Hadimba Devi Mandir","3 River Beas"," Manali","In Hinduism, Manu was said to have survived a great flood that destroyed the rest of the world. He then recreated mankind in this Kullu Valley town. What a rush, huh? No wonder the towering peaks and verdant terrain of Manali attracts adventure"));
        poularTourModels.add(new PoularTourModel(R.drawable.image16,"1 Old Goa","2 Dudhsagar Falls","3 Anjuna Market","Goa","East meets West in this sun-soaked state, where Indian culture intertwines with Portuguese influences left over from a 500-year occupation. The beaches have long served as a magnet for serene hedonists."));
        poularTourModels.add(new PoularTourModel(R.drawable.image17,"1 Amber (Amer) Fort and Palace","2 Jantar Mantar – Jaipur","3 Palace of Wind (Hawa Mahal)","Jaipur","If you take one look at the glorious stucco buildings that line Jaipur’s wide streets, you’ll understand why this is nicknamed “The Pink City.” Spend your days exploring City Palace, Hawa Mahal, and Amber and Jaigarh forts. "));
        poularTourModels.add(new PoularTourModel(R.drawable.image18,"1 Tibetan Market","2 Phyang Monastery","3 Leh Royal Palace","Leh","The Ladakh capital city of Leh lies near the eastern parts of Jammu and Kashmir, on the crossroads of the historic “Silk Route” from Sinkiang to West Asia and to the plains of India. The humbling monasteries of Shey, Hemis, Alchi, Thikse and Lamayuru "));
        poularTourModels.add(new PoularTourModel(R.drawable.image19,"1 Mysore Zoo – Sri Chamarajendra Zoological Gardens","2 Maharajah’s Palace (Amba Vilas)","3 Somnathpur Temples","Mysore","The kings of the Wodeyar dynasty set the bar high for the southern cultural capital of Mysore. Ornate palaces and the Gothic St. Philomena’s Church with its 175-foot spires pack a visual punch; local institutions keep Carnatic classical music"));
        poularTourModels.add(new PoularTourModel(R.drawable.image20,"1 Dilwara Jain Temples","2 Sunset Point Mount Abu","3 Nakki Lake","Mount Abu","Situated amidst lush green, forested hills on the highest peak in the Aravali range, Mount Abu is the summer capital for the Indian state of Rajasthan. Its cool and soothing climate makes it an ideal retreat. Explore a number of Jain temples,"));
        poularTourModels.add(new PoularTourModel(R.drawable.image21,"1 Darbar Sahib","2 Jallianwala Bagh","3 Harmandir Sahib","Amritsar","Amritsar is a major commercial and cultural centre in the heart of Punjab. The city is the spiritual and cultural centre of the Sikh religion and is home to the Harmandir Sahib, also known as the Golden Temple. Respectfully marvel at the Indian and "));
        poularTourModels.add(new PoularTourModel(R.drawable.image22,"1 Kalka – Shimla Railway","2 Kinnaur","3 Jakhu Temple","Shimla","Snow-capped Himalayan peaks and green pastures surround Shimla, the capital of Himachal Pradesh. Enjoy a heritage walk through Shimla’s Victorian-era architecture, labyrinthine bazaars and lengthy pedestrian shopping mall."));
        poularTourModels.add(new PoularTourModel(R.drawable.image23,"1 Dal Lake","2 Mughal Gardens","3 Jame Mosque","Kashmir","Situated amidst lush green, forested hills on the highest peak in the Aravali range, Mount Abu is the summer capital for the Indian state of Rajasthan. Its cool and soothing climate makes it an ideal retreat. Explore a number of Jain temples,"));
        poularTourModels.add(new PoularTourModel(R.drawable.image5,"1 Laxman Jhula","2 Swarg Ashram","3 Neelkanth Mahadev","Rishikesh","The Ladakh capital city of Leh lies near the eastern parts of Jammu and Kashmir, on the crossroads of the historic “Silk Route” from Sinkiang to West Asia and to the plains of India. The humbling monasteries of Shey, Hemis, Alchi, Thikse and Lamayuru "));
        poularTourModels.add(new PoularTourModel(R.drawable.image6,"1 Mansa Devi Temple","2 Chandi Devi Temple","3 Har ki Pauri","Haridwar","The kings of the Wodeyar dynasty set the bar high for the southern cultural capital of Mysore. Ornate palaces and the Gothic St. Philomena’s Church with its 175-foot spires pack a visual punch; local institutions keep Carnatic classical music"));
        poularTourModels.add(new PoularTourModel(R.drawable.image7,"1 Victoria Memorial Hall","2 Mother House","3 Kalighat Kali Temple"," Kolkata","Situated amidst lush green, forested hills on the highest peak in the Aravali range, Mount Abu is the summer capital for the Indian state of Rajasthan. Its cool and soothing climate makes it an ideal retreat. Explore a number of Jain temples,"));



       PopularTourAdapter popularTourAdapter=new PopularTourAdapter(this,poularTourModels);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(popularTourAdapter);


    }
}
