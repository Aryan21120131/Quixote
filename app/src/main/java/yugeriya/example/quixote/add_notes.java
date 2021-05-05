package yugeriya.example.quixote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class add_notes extends AppCompatActivity {


    int SELECT_IMAGE_CODE=1;
    private Uri ImageUri;
    private ImageView edit_image;
    private int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        final EditText edit_note=findViewById(R.id.description);
        final EditText edit_title=findViewById(R.id.title_add);
        edit_image=findViewById(R.id.imag);
        Button btn=findViewById(R.id.save);
        Button select=findViewById(R.id.select);

        Intent update=getIntent();
        final int id=update.getIntExtra("Index",0);
        if(id!=-1){
            edit_note.setText(MainActivity.description.get(id));
            edit_title.setText(MainActivity.Title.get(id));
            edit_image.setImageURI(MainActivity.images.get(id));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id!=-1){
                    MainActivity.description.set(id,edit_note.getText().toString());
                    MainActivity.Title.set(id,edit_title.getText().toString());
                    MainActivity.images.set(id,ImageUri);
                    MainActivity.notesAdapter.notifyDataSetChanged();
                }
                else {
                    MainActivity.description.add(edit_note.getText().toString());
                    MainActivity.Title.add(edit_title.getText().toString());
                    MainActivity.images.add(ImageUri);
                    MainActivity.notesAdapter.notifyDataSetChanged();
                }
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1) {
            ImageUri=data.getData();
            edit_image.setImageURI(ImageUri);
        }
    }

}