package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SolutionStorage implements ISolutionStorage {
  private final String SOLUTION_SUFFIX = "_solution";
  private final Context context;

  public SolutionStorage(Context context) {
    this.context = context;
  }

  @Override
  public boolean saveSolution(@NonNull Bitmap solution, @NonNull WritableCharacter character, int currentGuideType) {
    try (FileOutputStream outputStream = context.openFileOutput(character.getBaseFileName() + SOLUTION_SUFFIX,
      Context.MODE_PRIVATE)) {
      return solution.compress(Bitmap.CompressFormat.WEBP, 90, outputStream);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean solutionExists(@NonNull WritableCharacter character) {
    return Arrays.binarySearch(context.fileList(), character.getBaseFileName() + SOLUTION_SUFFIX) >= 0;
  }

  @Nullable
  @Override
  public Bitmap readSolution(@NonNull WritableCharacter character) {
    try (FileInputStream fis = context.openFileInput(character.getBaseFileName() + SOLUTION_SUFFIX)) {
      return BitmapFactory.decodeStream(fis);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean removeSolution(@NonNull WritableCharacter character) {
    return !solutionExists(character) || context.deleteFile(character.getBaseFileName() + SOLUTION_SUFFIX);
  }
}