package com.example.captonesecondstage.DataBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddingReadingData {
    DatabaseReference dbRef ;
   public  AddingReadingData(){
       dbRef= FirebaseDatabase.getInstance().getReference();
   }
   public  AddingReadingData(String reference){
       dbRef= FirebaseDatabase.getInstance().getReference(reference);
   }
}
