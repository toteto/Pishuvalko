package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
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
  public boolean saveSolution(@NonNull Bitmap solution, @NonNull WritableCharacter character,
    @WritableCharacter.GuidesType int guideType) {
    try (FileOutputStream outputStream = context.openFileOutput(generateFilename(character, guideType),
      Context.MODE_PRIVATE)) {
      return solution.compress(Bitmap.CompressFormat.WEBP, 90, outputStream);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean solutionExists(@NonNull WritableCharacter character, @WritableCharacter.GuidesType int guideType) {
    final String[] solutions = context.fileList();
    Arrays.sort(solutions);
    return Arrays.binarySearch(solutions, generateFilename(character, guideType)) >= 0;
  }

  @Nullable
  @Override
  public Bitmap readSolution(@NonNull WritableCharacter character, int guideType) {
    try (FileInputStream fis = context.openFileInput(generateFilename(character, guideType))) {
      return BitmapFactory.decodeStream(fis);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean removeSolution(@NonNull WritableCharacter character, @WritableCharacter.GuidesType int guideType) {
    return !solutionExists(character, guideType) || context.deleteFile(generateFilename(character, guideType));
  }

  @Override
  public void approveSolution(@NonNull WritableCharacter character) {
    getDefaultSharedPreferencesEditor().putBoolean(character.getBaseFileName() + SOLUTION_SUFFIX, true).commit();
  }

  @Override
  public void declineSolution(@NonNull WritableCharacter character) {
    getDefaultSharedPreferencesEditor().putBoolean(character.getBaseFileName() + SOLUTION_SUFFIX, false).commit();
  }

  @Nullable
  @Override
  public Boolean isSolutionApproved(@NonNull WritableCharacter character) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
    if (sp.contains(character.getBaseFileName() + SOLUTION_SUFFIX)) {
      return sp.getBoolean(character.getBaseFileName() + SOLUTION_SUFFIX, false);
    } else {
      return null;
    }
  }

  private String generateFilename(@NonNull WritableCharacter character, @WritableCharacter.GuidesType int guideType) {
    return character.getBaseFileName() + guideType + SOLUTION_SUFFIX;
  }

  private SharedPreferences.Editor getDefaultSharedPreferencesEditor() {
    return PreferenceManager.getDefaultSharedPreferences(context).edit();
  }
}
