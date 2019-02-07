package company.inside.sangue.business.adapter


//class ImageAdapter:BaseAdapter(){
//        /** The context. */
//        lateinit var context:Activity
//
//        /**
//         * Instantiates a new image adapter.
//         *
//         * @param localContext
//         *            the local context
//         */
//        fun ImageAdapter( localContext:Activity) {
//            context = localContext;
//            images = getAllShownImagesPath(context);
//        }
//
//        fun getCount() {
//            return images.size();
//        }
//
//        public Object getItem(int position) {
//            return position;
//        }
//
//        public long getItemId(int position) {
//            return position;
//        }
//
//        public View getView(final int position, View convertView,
//                ViewGroup parent) {
//            ImageView picturesView;
//            if (convertView == null) {
//                picturesView = new ImageView(context);
//                picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                picturesView
//                        .setLayoutParams(new GridView.LayoutParams(270, 270));
//
//            } else {
//                picturesView = (ImageView) convertView;
//            }
//
//            Glide.with(context).load(images.get(position))
//                    .placeholder(R.drawable.ic_launcher).centerCrop()
//                    .into(picturesView);
//
//            return picturesView;
//        }
//
//        /**
//         * Getting All Images Path.
//         *
//         * @param activity
//         *            the activity
//         * @return ArrayList with images Path
//         */
//        private ArrayList<String> getAllShownImagesPath(Activity activity) {
//            Uri uri;
//            Cursor cursor;
//            int column_index_data, column_index_folder_name;
//            ArrayList<String> listOfAllImages = new ArrayList<String>();
//            String absolutePathOfImage = null;
//            uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//            String[] projection = { MediaColumns.DATA,
//                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
//
//            cursor = activity.getContentResolver().query(uri, projection, null,
//                    null, null);
//
//            column_index_data = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
//            column_index_folder_name = cursor
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//            while (cursor.moveToNext()) {
//                absolutePathOfImage = cursor.getString(column_index_data);
//
//                listOfAllImages.add(absolutePathOfImage);
//            }
//            return listOfAllImages;
//        }
//    }
//}