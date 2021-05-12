package io.github.portlek.bukkitlocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.cactoos.iterable.IterableOf;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class RandomUtilTest {

  @Test
  void chooseRandoms() {
    final var nonduplicatedlist = List.of(1, 1, 1, 1, 5, 6, 7, 8, 9);
    final var nonduplicated = RandomUtil.chooseRandoms(nonduplicatedlist, 5, false);
    new Assertion<>(
      "Couldn't choose correct amount of random numbers!",
      nonduplicated.size(),
      new IsEqual<>(5)
    ).affirm();
    final var duplicates = new ArrayList<Integer>();
    new Assertion<>(
      "Numbers are duplicated!",
      nonduplicated.stream().anyMatch(integer -> {
        if (duplicates.contains(integer)) {
          return true;
        }
        duplicates.add(integer);
        return false;
      }),
      new IsNot<>(new IsTrue())
    ).affirm();
    duplicates.clear();
    final var duplicatedlist = List.of(1, 1, 1, 1, 5);
    final var duplicated = RandomUtil.chooseRandoms(duplicatedlist, 5, true);
    new Assertion<>(
      "Couldn't choose correct amount of random numbers!",
      duplicated.size(),
      new IsEqual<>(5)
    ).affirm();
    new Assertion<>(
      "Numbers are not duplicated!",
      duplicated.stream().anyMatch(integer -> {
        if (duplicates.contains(integer)) {
          return true;
        }
        duplicates.add(integer);
        return false;
      }),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "There are duplicated number more than 2!",
      duplicates.size(),
      new AnyOf<>(
        new IterableOf<>(
          new IsEqual<>(1),
          new IsEqual<>(2)
        )
      )
    ).affirm();
    duplicates.clear();
  }

  @Test
  void empty() {
    final var randoms = RandomUtil.chooseRandoms(Collections.emptyList(), 1, false);
    new Assertion<>(
      "It's not empty",
      randoms,
      new IsEmptyCollection<>()
    ).affirm();
  }
}
