package com.example.akash_raj.myfilemanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by akash on 6/4/17.
 */

public class Files_Subfolders {
    private File location;
    private String name;
    private String dt_modified;
    private boolean is_file;
    private long size;
    private boolean is_img;
    private boolean is_aud;
    private boolean is_vid;
    private boolean is_doc;
    private boolean is_txt;
    private boolean is_readable;
    private boolean is_writable;
    private boolean is_executable;

    //Constructors

    public Files_Subfolders(File location)
    {
        this.location=location;
        checkFileType();
        is_executable();
        is_readable();
        is_writable();
        setDt_modified();
        setname();
        setSize();
        setIs_file();
    }

    //Getters for easy access

    public boolean is_img() {
        return is_img;
    }

    public boolean is_aud() {
        return is_aud;
    }

    public boolean is_vid() {
        return is_vid;
    }

    public boolean is_doc() {
        return is_doc;
    }

    public boolean is_txt() {
        return is_txt;
    }

    public boolean is_readable() {
        return is_readable;
    }

    public boolean is_writable() {
        return is_writable;
    }

    public boolean is_executable() {
        return is_executable;
    }

    public File getLocation() {
        return location;
    }

    public String getname() {
        return name;
    }

    public String getDt_modified() {
        return dt_modified;
    }

    public boolean is_file() {
        return is_file;
    }

    public long getSize() {
        return size;
    }

    //Setters


    public void setIs_readable() {
        this.is_readable = location.canRead();
    }

    public void setIs_writable() {
        this.is_writable = location.canWrite();
    }

    public void setIs_executable() {
        this.is_executable =location.canExecute();
    }

    public void setname() {
        this.name = extractName(location.toString());
    }

    public void setDt_modified() {
        Date date=new Date(location.lastModified());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        this.dt_modified = simpleDateFormat.format(date);
    }

    public void setIs_file() {
        this.is_file = location.isFile();
    }

    public void setSize() {
        this.size =/*location.length()*/calculateSize(location);
    }

    //Methods


    public String extractName(String path)
    {
        String name;
        int i=path.lastIndexOf("/");
        if(i<path.length()) {
            name= path.substring(i+1);
        }
        else
        {
            return "/";
        }
        return name;
    }


    public long calculateSize(File curr_root)
    {
        long totalSize=0;
        File[] arr=curr_root.listFiles();
        if(arr!=null && arr.length>0) {
            for (File f : arr) {
                if (!f.exists()) {
                    continue;
                }
                if (f.isDirectory()) {
                    totalSize += calculateSize(f);
                } else if (f.isFile()) {
                    totalSize += f.length();
                }
            }
        }
        return totalSize;
    }


    public String getExtension(String s)
    {
        int i=s.lastIndexOf(".");
        if(i<0){ return null; }
        else if(i<s.length()-1){ return null; }
        else
        {
            return s.substring(i+1);
        }
    }


    public void checkFileType()
    {
        if(location.isFile())
        {
            String ext=getExtension(location.toString());
            if(ext!=null) {
                ext = ext.toLowerCase();
                if (ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg") || ext.equals("bmp") || ext.equals("gif")) {
                    is_img = true;
                    return;
                } else if (ext.equals("mp3") || ext.equals("wav")) {
                    is_aud = true;
                    return;
                } else if (ext.equals("pdf") || ext.equals("doc")) {
                    is_doc = true;
                    return;
                } else if (ext.equals("mp4") || ext.equals("wmv") || ext.equals("mov") || ext.equals("avi") || ext.equals("flv")) {
                    is_vid = true;
                    return;
                } else if (ext.equals("txt")) {
                    is_txt = true;
                    return;
                }
            }
        }
    }

    public void getSubFiles(ArrayList<Files_Subfolders> ret_data)
    {
        ret_data.clear();
        ArrayList<Files_Subfolders> dirs=new ArrayList<>();
        ArrayList<Files_Subfolders> file=new ArrayList<>();
        dirs.clear();file.clear();
        if(location.exists()) {
            File arr[] = location.listFiles();
            if (arr!=null && arr.length > 0) {
                for (int i=0;i<arr.length;i++) {
                    File f=arr[i];
                    if (f.exists() && f.isDirectory()) {
                        dirs.add(new Files_Subfolders(f));
                    } else if (f.exists() && f.isFile()) {
                        file.add(new Files_Subfolders(f));
                    }
                }
                if (!file.isEmpty()) {
                    dirs.addAll(file);
                }
                if(!dirs.isEmpty()){
                    ret_data.addAll(dirs);
                }
            }
        }
    }

    public boolean rename(Files_Subfolders f,String str)
    {
        int i=f.location.toString().lastIndexOf("/");
        str=location.toString().substring(0,i+1)+str;
        f.location.renameTo(new File(str));
        return true;
    }
}
