/*
 * This example reads the names of all contacts and displays them
 * in a ListView widget. Selecting a name shows a Toast with the name.
 * Be sure to notice the permission in the Manifest. Since this permission is
 * considered dangerous, a Setting was made for the app to run.
 */
package com.course.example.cursoradapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.net.Uri;
import android.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class CursorAdapter extends ListActivity {
    
	Cursor cursor = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
		+ " COLLATE LOCALIZED ASC";
       
        cursor = getContentResolver().query(uri, null, null, null, sortOrder);
        
        String[] cols = new String[] {ContactsContract.Contacts.DISPLAY_NAME};
        int[] views = new int[] {android.R.id.text1};
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, 
        		cursor, cols, views, 0);
        setListAdapter(adapter);
             
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		cursor.moveToPosition(position);
		String name = cursor.getString(
				cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }
}