package yugeriya.example.quixote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> names,descriptions;
    ArrayList<Uri> img;
    LayoutInflater inflater;
    static int Length;

    public NotesAdapter(Context context, ArrayList<String> names, ArrayList<String> description,ArrayList<Uri> img){
        this.context=context;
        this.names=names;
        this.descriptions=description;
        this.img=img;
        inflater=(LayoutInflater.from(context.getApplicationContext()));
    }

    @Override
    public int getCount() {
        Length=names.size();
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_notes_card,null);

        TextView name=view.findViewById(R.id.name_card);
        TextView description=view.findViewById(R.id.description_card);
        ImageView imageView=view.findViewById(R.id.image_card);

        name.setText(names.get(i));
        description.setText(descriptions.get(i));
        if(img.get(i)==null) {
            imageView.setImageResource(R.drawable.back);
        }
        else
            imageView.setImageURI(img.get(i));
        return view;
    }
}
