package asyncTasks;

/**
 * Created by android on 7/15/15.
 */

public class EpisodeAsyncTask extends AsyncTask<Params, Progress, Result> {
    private OnOperationListener mListener;
    public EpisodeAsyncTask(OnOperationListener listener) { mListener = listener; }

    protected void onPreExecute() { }
    protected Result doInBackground(Params... params) {}
    protected void onProgressUpdate(Progress... values) {}
    protected void onPostExecute(Result result) { mListener.onOperationSuccess(); }
}