package com.example.chefgiraffe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class TableActivity extends BaseActivity {
    public static final String VAR_TABLE_ID = "table-id";

    @Override
    protected int contentView() {
        return R.layout.activity_table;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String tableId = intent.getStringExtra(VAR_TABLE_ID);
    }
}
