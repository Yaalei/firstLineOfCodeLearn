package com.example.splitapplicationdemo.recyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitapplicationdemo.R;
import com.example.splitapplicationdemo.embedding.DetailActivity;
import com.example.splitapplicationdemo.embedding.MainActivity;

import java.util.List;

/**
 * 创建一个BlogAdapter类，继承RecyclerView.Adapter，并指定泛型为BlogAdapter.ViewHolder
 * 创建一个ViewHolder内部类，在构造方法中
 */
public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    private List<Blog> mBlogList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView blogTitle;
        TextView blogSummery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blogTitle = itemView.findViewById(R.id.blog_title);
            blogSummery = itemView.findViewById(R.id.blog_summery);
        }
    }

    public BlogAdapter(List<Blog> poetList) {
        mBlogList = poetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.blogTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                Blog blog = mBlogList.get(position);
                Intent intent = new Intent(parent.getContext(), DetailActivity.class);
                intent.putExtra("title",blog.getBlogTitle());
                MainActivity activity = (MainActivity) parent.getContext();
                activity.startActivity(intent);
            }
        });
        viewHolder.blogSummery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                Blog blog = mBlogList.get(position);
                Intent intent = new Intent(parent.getContext(), DetailActivity.class);
                intent.putExtra("title",blog.getBlogTitle());
                MainActivity activity = (MainActivity) parent.getContext();
                activity.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Blog blog = mBlogList.get(position);
        holder.blogTitle.setText(blog.getBlogTitle());
        holder.blogSummery.setText(blog.getBlogSummary());
    }

    @Override
    public int getItemCount() {
        return mBlogList.size();
    }


}
