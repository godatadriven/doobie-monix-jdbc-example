package com

import monix.eval.Task
import cats.effect.Async

package object godatadriven {
  implicit object monixTaskAsync extends Async[Task] {
    def pure[A](a: A): Task[A] = Task.pure(a)
    def flatMap[A, B](a: Task[A])(f: A => Task[B]): Task[B] = a.flatMap(f)
    def suspend[A](fa: => Task[A]): Task[A] = Task.suspend(fa)
    def handleErrorWith[A](fa: Task[A])(f: Throwable => Task[A]): Task[A] = fa.onErrorHandleWith(f)
    def raiseError[A](e: Throwable): Task[A] = Task.raiseError(e)
    def async[A](k: (scala.util.Either[Throwable, A] => Unit) => Unit): Task[A] = ???
    def tailRecM[A, B](a: A)(f: A => Task[Either[A, B]]): Task[B] = Task.tailRecM(a)(f)
  }
}
