/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.resources;

import client.MultipleInstanceException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Syynth
 */
public class ResourceManager 
{
    
    private static ResourceManager gID;
    
    private HashMap rMap;
    public ResourceManager() throws MultipleInstanceException
    {
        if (gID != null)
            throw new MultipleInstanceException("You can only have one " +
                    " instance of the singleton class ResourceManager");
        gID = this;
        
        rMap = new HashMap();
        init();
    }
    
    private void init()
    {
        File assetsDir = new File("assets");
        //load Textures
        File[] textureFiles = listFilesAsArray(assetsDir,new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("png");
            }
        },true);
        
        for(int i = 0; i<textureFiles.length; i++)
        {
            String resourceName = textureFiles[i].getParentFile().getName();
            //if the other assets from the resource has not already been loaded
            if(!rMap.containsKey(textureFiles[i].getParent()))
            {
                //Add the new Resource
                
                rMap.put(resourceName,new Resource(resourceName));
            }           
                Resource tmpR = (Resource)rMap.get(resourceName);
                InputStream tmpT = ResourceLoader.getResourceAsStream(textureFiles[i].getPath());
                String textureName = textureFiles[i].getName().replace(".png","");
                tmpR.addTexture(textureName,tmpT);
        }
       
        //load Audio Files
       File[] audioFiles = listFilesAsArray(assetsDir,new FilenameFilter() {

       @Override
       public boolean accept(File dir, String name) {
            return name.endsWith("ogg");
            }
            },true);
        for(int i = 0; i<audioFiles.length; i++)
        {
            String resourceName = audioFiles[i].getParentFile().getName();
            //if the other assets from the resource has not already been loaded
            if(rMap.containsKey(audioFiles[i].getParent()))
            {
                
                rMap.put(resourceName,new Resource(resourceName));             
            }
                Resource tmpR = (Resource)rMap.get(resourceName);
                InputStream tmpA = ResourceLoader.getResourceAsStream(audioFiles[i].getPath());
                String audioName = audioFiles[i].getName().replace(".ogg","");
                tmpR.addSound(audioName,tmpA);
        }
    }
    public Resource getResource(String name)
    {
        return (Resource)rMap.get(name);
    }
private File[] listFilesAsArray(
		File directory,
		FilenameFilter filter,
		boolean recurse)
{
	Collection<File> files = listFiles(directory,
			filter, recurse);
//Java4: Collection files = listFiles(directory, filter, recurse);
	
	File[] arr = new File[files.size()];
	return files.toArray(arr);
}

private Collection<File> listFiles(
// Java4: public static Collection listFiles(
		File directory,
		FilenameFilter filter,
		boolean recurse)
{
	// List of files / directories
	Vector<File> files = new Vector<File>();
// Java4: Vector files = new Vector();
	
	// Get files / directories in the directory
	File[] entries = directory.listFiles();
	
	// Go over entries
	for (File entry : entries)
	{
// Java4: for (int f = 0; f < files.length; f++) {
// Java4: 	File entry = (File) files[f];

		// If there is no filter or the filter accepts the 
		// file / directory, add it to the list
		if (filter == null || filter.accept(directory, entry.getName()))
		{
			files.add(entry);
		}
		
		// If the file is a directory and the recurse flag
		// is set, recurse into the directory
		if (recurse && entry.isDirectory())
		{
			files.addAll(listFiles(entry, filter, recurse));
		}
	}
	
	// Return collection of files
	return files;
}

    public static ResourceManager getResourceManager()
    {
        return gID;
    }
}
