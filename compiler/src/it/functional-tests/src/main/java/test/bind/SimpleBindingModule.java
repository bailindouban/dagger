/*
 * Copyright (C) 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.bind;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Singleton;
import test.SomeQualifier;

@Module(includes = InterfaceModule.class)
abstract class SimpleBindingModule {
  @Binds
  abstract Object bindObject(FooOfStrings impl);

  @Binds
  abstract Foo<String> bindFooOfStrings(FooOfStrings impl);

  @Binds
  abstract Foo<? extends Number> bindFooOfNumbers(Foo<Integer> fooOfIntegers);

  @Binds
  @Singleton
  @SomeQualifier
  abstract Foo<String> bindQualifiedFooOfStrings(FooOfStrings impl);

  @Provides
  static Foo<Integer> provideFooOfIntegers() {
    return new Foo<Integer>() {};
  }

  @Provides
  static Foo<Double> provideFooOfDoubles() {
    return new Foo<Double>() {};
  }

  @Binds
  @IntoSet
  abstract Foo<? extends Number> bindFooOfIntegersIntoSet(Foo<Integer> fooOfIntegers);

  @Binds
  @IntoSet
  abstract Foo<? extends Number> bindFooExtendsNumberIntoSet(Foo<Double> fooOfDoubles);

  @Binds
  @ElementsIntoSet
  abstract Set<Object> bindSetOfFooNumbersToObjects(Set<Foo<? extends Number>> setOfFooNumbers);

  @Binds
  @IntoSet
  abstract Object bindFooOfStringsIntoSetOfObjects(FooOfStrings impl);

  @Provides
  static HashSet<String> provideStringHashSet() {
    return new HashSet<>(Arrays.asList("hash-string1", "hash-string2"));
  }

  @Provides
  static TreeSet<CharSequence> provideCharSequenceTreeSet() {
    return new TreeSet<CharSequence>(Arrays.asList("tree-charSequence1", "tree-charSequence2"));
  }

  @Provides
  static Collection<CharSequence> provideCharSequenceCollection() {
    return Arrays.<CharSequence>asList("list-charSequence");
  }

  @Binds
  @ElementsIntoSet
  abstract Set<CharSequence> bindHashSetOfStrings(HashSet<String> set);

  @Binds
  @ElementsIntoSet
  abstract Set<CharSequence> bindTreeSetOfCharSequences(TreeSet<CharSequence> set);

  @Binds
  @ElementsIntoSet
  abstract Set<CharSequence> bindCollectionOfCharSequences(Collection<CharSequence> collection);
}
