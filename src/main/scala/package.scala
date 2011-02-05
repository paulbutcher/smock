package com.paulbutcher

package object smock {
  def mock[T: ClassManifest]: T = Proxy.create(classManifest[T].erasure).asInstanceOf[T]
}
