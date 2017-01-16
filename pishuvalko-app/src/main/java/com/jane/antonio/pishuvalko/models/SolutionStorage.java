package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SolutionStorage implements ISolutionStorage {
  private static final String SOLUTION_SUFFIX = "_solution";
  private final Context context;

  public SolutionStorage(Context context) {
    this.context = context;
  }

  @Override
  public boolean saveSolution(@NonNull Bitmap solution, @NonNull WritableCharacter character) {
    try (FileOutputStream outputStream = context.openFileOutput(generateFilename(character), Context.MODE_PRIVATE)) {
      resetApprovalStatus(character);
      return solution.compress(Bitmap.CompressFormat.WEBP, 90, outputStream);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean solutionExists(@NonNull WritableCharacter character) {
    final String[] solutions = context.fileList();
    Arrays.sort(solutions);
    return Arrays.binarySearch(solutions, generateFilename(character)) >= 0;
  }

  @Nullable
  @Override
  public Bitmap readSolution(@NonNull WritableCharacter character) {
    try (FileInputStream fis = context.openFileInput(generateFilename(character))) {
      return BitmapFactory.decodeStream(fis);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean removeSolution(@NonNull WritableCharacter character) {
    return !solutionExists(character) || context.deleteFile(generateFilename(character));
  }

  @Override
  public void resetApprovalStatus(@NonNull WritableCharacter character) {
    getSharedPreferences(character).edit().remove(generateFilename(character)).apply();
  }

  @Override
  public void approveSolution(@NonNull WritableCharacter character) {
    getSharedPreferences(character).edit().putBoolean(generateFilename(character), true).apply();
  }

  /** Calls the appropriate accept and decline methods based on the provided values. */
  public void approveSolution(@NonNull WritableCharacter character, boolean approved) {
    if (approved) {
      approveSolution(character);
    } else {
      declineSolution(character);
    }
  }

  @Override
  public void declineSolution(@NonNull WritableCharacter character) {
    getSharedPreferences(character).edit().putBoolean(generateFilename(character), false).apply();
  }

  @Nullable
  @Override
  public Boolean isSolutionApproved(@NonNull WritableCharacter character) {
    SharedPreferences sp = getSharedPreferences(character);
    final String charFilename = generateFilename(character);
    if (sp.contains(charFilename)) {
      return sp.getBoolean(charFilename, false);
    } else {
      return null;
    }
  }

  private String generateFilename(@NonNull WritableCharacter character) {
    return character.getBaseFileName() + SOLUTION_SUFFIX;
  }

  private SharedPreferences getSharedPreferences(@NonNull WritableCharacter character) {
    return context.getSharedPreferences(SOLUTION_SUFFIX, Context.MODE_PRIVATE);
  }
}
