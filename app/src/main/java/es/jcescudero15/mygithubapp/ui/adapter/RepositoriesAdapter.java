package es.jcescudero15.mygithubapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.jcescudero15.mygithubapp.R;
import es.jcescudero15.mygithubapp.ui.viewmodel.RepositoryViewModel;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

    public interface OnRepositoryClickListener {
        void onRepositoryClicked(RepositoryViewModel repository, ImageView profileImage);
    }


    private List<RepositoryViewModel> mRepositoryList;
    private OnRepositoryClickListener mListener;
    private Context mContext;


    public RepositoriesAdapter(OnRepositoryClickListener listener, Context context) {
        this.mRepositoryList = new ArrayList<>();
        this.mListener = listener;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_repository, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(mRepositoryList.get(i));
    }

    @Override
    public int getItemCount() {
        return mRepositoryList.size();
    }


    public void clearData() {
        mRepositoryList.clear();
        notifyDataSetChanged();
    }

    public void addData(List<RepositoryViewModel> repositories) {
        mRepositoryList.addAll(repositories);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_repository_name)
        TextView repositoryName;

        @BindView(R.id.item_repository_description)
        TextView repositoryDescription;

        @BindView(R.id.item_repository_image)
        ImageView repositoryImage;


        private RepositoryViewModel repository;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        void bind(RepositoryViewModel repositoryViewModel) {
            repository = repositoryViewModel;

            repositoryName.setText(String.format("%s/%s", repositoryViewModel.getOwnerName(), repositoryViewModel.getName()));
            repositoryDescription.setText(repositoryViewModel.getDescription());

            Glide.with(mContext)
                    .load(repositoryViewModel.getImageUrl())
                    .into(repositoryImage);
        }


        @OnClick(R.id.item_repository_main_layout)
        void onRepositoryClicked() {
            mListener.onRepositoryClicked(repository, repositoryImage);
        }
    }
}
